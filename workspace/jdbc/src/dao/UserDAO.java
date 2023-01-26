package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import domain.UserVO;

public class UserDAO {

   public static Long userId;   // 로그인 했을떄의 유저의 ID를 보관하기 위해 Static 변수로 선언.
   public Connection connection; //연결 객체
   public PreparedStatement preparedStatement; //쿼리 관리 객체
   public ResultSet resultSet; //결과 테이블 객체
   
//   아이디 중복검사
   public boolean checkId(String userIdentification) {
      String query = "SELECT COUNT(USER_ID) FROM TBL_USER WHERE USER_IDENTIFICATION = ?";
      boolean result = false;
      connection = DBConnecter.getConnection();   //DBconnecter의 getConnection 메소드를 불러와서 connection에 담는다.
      try {
         preparedStatement = connection.prepareStatement(query);   //conecntion 클레스 안에있는 prepareStatement 메소드를 불러옴.
         preparedStatement.setString(1, userIdentification);   // preparedStatement는 쿼리문을 좀더 쉽게 전달하기 위한 메소드임.
         resultSet = preparedStatement.executeQuery();   // select를 사용할때는 executeQuery를 사용 (쿼리문으로 전달받은 데이타들을 resultset으로 리턴함.)
         
         resultSet.next();   // resultSet의 행별로 넘어가면서 읽는다. 결과창이 단항이므로 while문에 묶지 않음. 내용이 있는지 없는지 boolean 타입으로 리턴함.
         result = resultSet.getInt(1) == 0;   // count를 사용하여 몇개인지 판단. 개수임으로 getInt를 사용. resultset의 값을 받아와 result에 담음.
         
      } catch (SQLException e) {
         System.out.println("checkId(String) SQL문 오류");   //쿼리문의 오류가 있을 때 아래 익셉션 메세지 출력.
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if(resultSet != null) {   // 마지막에 open한 순서대로 다시 닫아줘야 함.
               resultSet.close();
            }
            if(preparedStatement != null) {
               preparedStatement.close();
            }
            if(connection != null) {
               connection.close();
            }
         } catch (SQLException e) {
            throw new RuntimeException(e);
         }
      }
      return result;   //불린타입의 result값을 리턴함.
   }
   
//   회원가입
   public void insert(UserVO userVO) {   // 유저의 전체 정보를 입력해야 함으로 UserVO타입의 객체를 매게변수로 받는다. // 화면쪽에서 UserVO객체를 만듬.
       String queryForJoin = "INSERT INTO TBL_USER"
                  + "(USER_ID, USER_IDENTIFICATION, USER_NAME, USER_PASSWORD, USER_PHONE, USER_EMAIL, USER_ADDRESS, USER_RECOMMENDER_ID) "
                  + "VALUES(SEQ_USER.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
       
//       기존 테이블에서 추천수 컬럼을 추가하여, 회원 정보 조회 시 추천 수도 같이 가져오도록 설계
//       String queryForUpdateRecommendCount = 
//             "UPDATE TBL_USER SET RECOMMEND_COUNT = RECOMMEND_COUNT + 1 WHERE USER_ID = ?";
       
//       만약 추천 수 조회가 잦을 경우, 테이블을 따로 분리하여 서브키를 설정한 뒤 조회
//       테이블이 분리되어서 회원가입 시 TBL_USER에 INSERT 후 TBL_RECOMMEND도 같이 INSERT가 필요하다.
//       이 때 TRIGGER를 사용하여 TBL_USER에 INSERT이벤트 발생 시 TBL_RECOMMEND에도 자동으로 INSERT 되도록 설계
       String queryForUpdateRecommendCount = 
             "UPDATE TBL_RECOMMEND SET RECOMMEND_COUNT = RECOMMEND_COUNT + 1 WHERE USER_ID = ?";   
       // 추천수를 확인하기위해 recommender_ID가 입력될때마다 TBL_RECOMMEND 테이블의 RECOMMEND_COUNT값을 하나씩 올려줌.
       
       connection = DBConnecter.getConnection();
       try {
         preparedStatement = connection.prepareStatement(queryForJoin);   // queryForJoin의 쿼리문을 DB에 전송
         preparedStatement.setString(1, userVO.getUserIdentification());   // ?의 순서대로 userVO객체의 값을 하나씩 넣어 isert해줌.
         preparedStatement.setString(2, userVO.getUserName());
         preparedStatement.setString(3, new String(Base64.getEncoder().encode(userVO.getUserPassword().getBytes())));
//         password는 Base64라는 클래스의 엔코더 메소드를 사용하여 비밀번호를 암호화해줌.
         preparedStatement.setString(4, userVO.getUserPhone());
         preparedStatement.setString(5, userVO.getUserEmail());
         preparedStatement.setString(6, userVO.getUserAddress());
         preparedStatement.setString(7, userVO.getUserRecommenderId());
         preparedStatement.executeUpdate();   // executeUpdate는 반영된 결과값을 개수를 리턴해주는 함수임.
         
         if(userVO.getUserRecommenderId() != null) {   // RecommenderId값을 입력했을때
            preparedStatement = connection.prepareStatement(queryForUpdateRecommendCount);   // queryForUpdateRecommendCount의 쿼리문을 DB에 전달
            preparedStatement.setString(1, userVO.getUserRecommenderId());   // ?에 들어갈 값을 String타입으로 set해줌.
            preparedStatement.executeUpdate();   // 위 쿼리문 수행후 수행된 결과값의 개수를 리턴.
         }

      } catch (SQLException e) {
         System.out.println("insert(UserVO) SQL문 오류");
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if(preparedStatement != null) {
               preparedStatement.close();
            }
            if(connection != null) {
               connection.close();
            }
         } catch (SQLException e) {
            throw new RuntimeException(e);
         }
      }
   }
//   로그인
   public boolean login(String userIdentification, String userPassword) {   // 유저의 아이디 및 비밀번호를 직접 입력하여 login을 하고 확인을 boolean타입(성공/실패).
      String query = "SELECT USER_ID FROM TBL_USER WHERE USER_IDENTIFICATION = ? AND USER_PASSWORD = ?";
      boolean check = false;
      
      connection = DBConnecter.getConnection();
      try {
         preparedStatement = connection.prepareStatement(query);
         preparedStatement.setString(1, userIdentification);
         preparedStatement.setString(2, userPassword);
         
         resultSet = preparedStatement.executeQuery();   //Select를할떄는 executeQuery를 사용하고 이것은 resultSet의 값을 리턴함.
         
         if(resultSet.next()) {   //읽어온 값이 있을 때
            userId = resultSet.getLong(1); //select문을 통해 받아온 user_id를 로그인 상태를 유지하여 다른 메소드를 사용하기위해 static변수로 설정한 userId에 담음
            check = true;   // 로그인 성공 실패를 리턴 하기 위한 check
         }
         
      } catch (SQLException e) {
         System.out.println("login(String, String) SQL문 오류");
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if(resultSet != null) {
               resultSet.close();
            }
            if(preparedStatement != null) {
               preparedStatement.close();
            }
            if(connection != null) {
               connection.close();
            }
         } catch (SQLException e) {
            throw new RuntimeException(e);
         }
      }
      return check;
   }
   
//   회원탈퇴
   public void delete() {
      String query = "DELETE FROM TBL_USER WHERE USER_ID = ?";   // 그 user_id에 해당하는 열을 지우는 쿼리문
      connection = DBConnecter.getConnection();
      try {
         preparedStatement = connection.prepareStatement(query);
         preparedStatement.setLong(1, userId);   // ?에 넣어줌
         
         preparedStatement.executeUpdate();   // 실행
         
      } catch (SQLException e) {
         System.out.println("delete() SQL문 오류");
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if(preparedStatement != null) {
               preparedStatement.close();
            }
            if(connection != null) {
               connection.close();
            }
         } catch (SQLException e) {
            throw new RuntimeException(e);
         }
      }
   }
   
//   아이디 찾기
   public String findIdentification(String userPhone) {   //아이디 찾을떄 userPhone을 입력하여 바로 찾기.
      String query = "SELECT USER_IDENTIFICATION FROM TBL_USER WHERE USER_PHONE = ?";
      String userIdentification = null;
      
      connection = DBConnecter.getConnection();
      try {
         preparedStatement = connection.prepareStatement(query);   //
         preparedStatement.setString(1, userPhone);   //

         resultSet = preparedStatement.executeQuery();
         
         if(resultSet.next()) {   //resultSet의 행을 하나씩 읽었는데 값이 있을 때
            userIdentification = resultSet.getString(1);
         }
         
      } catch (SQLException e) {
         System.out.println("findIdentification(String) SQL문 오류");
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if(resultSet != null) {
               resultSet.close();
            }
            if(preparedStatement != null) {
               preparedStatement.close();
            }
            if(connection != null) {
               connection.close();
            }
         } catch (SQLException e) {
            throw new RuntimeException(e);
         }
      }
      
      return userIdentification;
   }
   
//   비밀번호 변경
   public void updateUserPassword(String userPassword) {   // password를 직접 입력하여 변경
      String query = "UPDATE TBL_USER SET USER_PASSWORD = ? WHERE USER_ID = ?";
      connection = DBConnecter.getConnection();
      try {
         preparedStatement = connection.prepareStatement(query);
         preparedStatement.setString(1, new String(Base64.getEncoder().encode(userPassword.getBytes()))); //변경할 password를 암호화하여 ?에 담음
         preparedStatement.setLong(2, userId);   //static 변수의 userId를 2번째 ?에 담아줌. (로그인 했을때 비밀번호를 변경하기 위해서)
         
         preparedStatement.executeUpdate();   //수행한 결과 개수를리턴(비밀번호 변경완료)
         
      } catch (SQLException e) {
         System.out.println("updateUserPassword(String) SQL문 오류");
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if(preparedStatement != null) {
               preparedStatement.close();
            }
            if(connection != null) {
               connection.close();
            }
         } catch (SQLException e) {
            throw new RuntimeException(e);
         }
      }
   }
   
//   회원정보 수정
   public void update(UserVO userVO) {   //userVO 객체를 화면에서 받아 수정한다.
      String query = "UPDATE TBL_USER SET USER_NICKNAME = ?, USER_GENDER = ?, USER_BIRTH = ? WHERE USER_ID = ?";
      connection = DBConnecter.getConnection();
      try {
         preparedStatement = connection.prepareStatement(query);
         preparedStatement.setString(1, userVO.getUserNickname());
         preparedStatement.setString(2, userVO.getUserGender());
         preparedStatement.setString(3, userVO.getUserBirth());
         preparedStatement.setLong(4, userId);   //userId는 로그인시 static으로 저장해놓은 userId임
         
         preparedStatement.executeUpdate();   // 수행
         
      } catch (SQLException e) {
         System.out.println("update(UserVO) SQL문 오류");
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if(preparedStatement != null) {
               preparedStatement.close();
            }
            if(connection != null) {
               connection.close();
            }
         } catch (SQLException e) {
            throw new RuntimeException(e);
         }
      }
   }
   
//   회원정보 조회
   public UserVO select(Long userId) {   //유저의 id를 입력하여 정보를 조회
      String query = 
            "SELECT USER_ID, USER_IDENTIFICATION, USER_NAME, USER_PASSWORD,"
            + " USER_PHONE, USER_NICKNAME, USER_EMAIL, USER_ADDRESS, USER_BIRTH, "
            + "USER_GENDER, USER_RECOMMENDER_ID "
            + "FROM TBL_USER WHERE USER_ID = ?";
      UserVO userVO = null;
      connection = DBConnecter.getConnection();
      try {
         preparedStatement = connection.prepareStatement(query);
         preparedStatement.setLong(1, userId);   //원하는 userId 입력
         resultSet = preparedStatement.executeQuery();
         
         if(resultSet.next()) {   //불러온 값들을 resultSet에 저장한후 저장된 각각의 값들을 userVO객체에 set해줌
            userVO = new UserVO();
            userVO.setUserId(resultSet.getLong(1));
            userVO.setUserIdentification(resultSet.getString(2));
            userVO.setUserName(resultSet.getString(3));
            userVO.setUserPassword(resultSet.getString(4));
            userVO.setUserPhone(resultSet.getString(5));
            userVO.setUserNickname(resultSet.getString(6));
            userVO.setUserEmail(resultSet.getString(7));
            userVO.setUserAddress(resultSet.getString(8));
            userVO.setUserBirth(resultSet.getString(9));
            userVO.setUserGender(resultSet.getString(10));
            userVO.setUserRecommenderId(resultSet.getString(11));
         }
         
      } catch (SQLException e) {
         System.out.println("select(Long) SQL문 오류");
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if(resultSet != null) {
               resultSet.close();
            }
            if(preparedStatement != null) {
               preparedStatement.close();
            }
            if(connection != null) {
               connection.close();
            }
         } catch (SQLException e) {
            throw new RuntimeException(e);
         }
      }
      
      return userVO;
   }
//   추천수
   public Long getCountOfRecommenderId(Long userId) {
//      String query = "SELECT RECOMMEND_COUNT FROM TBL_USER WHERE USER_ID = ?";
      String query = "SELECT RECOMMEND_COUNT FROM TBL_RECOMMEND WHERE USER_ID = ?";
      Long recommendCount = 0L;
      connection = DBConnecter.getConnection();
      try {
         preparedStatement = connection.prepareStatement(query);
         preparedStatement.setLong(1, userId);
         resultSet = preparedStatement.executeQuery();
         
         if(resultSet.next()) {
            recommendCount = resultSet.getLong(1);   // 값을 받아와 recommencount에 담아줌
         }
      } catch (SQLException e) {
         System.out.println("getCountOfRecommenderId(Long) SQL문 오류");
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if(resultSet != null) {
               resultSet.close();
            }
            if(preparedStatement != null) {
               preparedStatement.close();
            }
            if(connection != null) {
               connection.close();
            }
         } catch (SQLException e) {
            throw new RuntimeException(e);
         }
      }
      return recommendCount;   // 리턴
   }
   
//   나를 추천한 사람
   public ArrayList<UserVO> selectRecommenders(){   //나를 추천한 사람들이 여러명일 수 있기 때문에 UserVO타입의 ArrayList에 저장하여 추천한 사람들의 모든 정보를 리턴
      String query = "SELECT USER_ID, USER_IDENTIFICATION, USER_NAME, USER_PASSWORD,"
            + " USER_PHONE, USER_NICKNAME, USER_EMAIL, USER_ADDRESS, USER_BIRTH, "
            + "USER_GENDER, USER_RECOMMENDER_ID FROM TBL_USER WHERE USER_RECOMMENDER_ID = ?";
      UserVO userVO = null;
      ArrayList<UserVO> users = new ArrayList<UserVO>();
      
      connection = DBConnecter.getConnection();
      try {
         preparedStatement = connection.prepareStatement(query);
         preparedStatement.setString(1, select(userId).getUserIdentification());   //로그인하여 저장된 userid를 이용하여 select메소드를 통해 회원정보를 받은후
//         그중 id를 받아와서 ?값에 넣어준다.
         
         resultSet = preparedStatement.executeQuery();
         
         while(resultSet.next()) { //한행씩 읽어 주며 값들을 userVO객체에 담아줌. // while문 이기때문에 더이상 값이 없을 때까지 읽어와서 입력해줌 
            userVO = new UserVO();
            userVO.setUserId(resultSet.getLong(1));
            userVO.setUserIdentification(resultSet.getString(2));
            userVO.setUserName(resultSet.getString(3));
            userVO.setUserPassword(resultSet.getString(4));
            userVO.setUserPhone(resultSet.getString(5));
            userVO.setUserNickname(resultSet.getString(6));
            userVO.setUserEmail(resultSet.getString(7));
            userVO.setUserAddress(resultSet.getString(8));
            userVO.setUserBirth(resultSet.getString(9));
            userVO.setUserGender(resultSet.getString(10));
            userVO.setUserRecommenderId(resultSet.getString(11));
            users.add(userVO);   // users라는 UserVO타입의 ArrayList에 add해준다
         }
         
      } catch (SQLException e) {
         System.out.println("selectRecommenders() SQL문 오류");
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if(resultSet != null) {
               resultSet.close();
            }
            if(preparedStatement != null) {
               preparedStatement.close();
            }
            if(connection != null) {
               connection.close();
            }
         } catch (SQLException e) {
            throw new RuntimeException(e);
         }
      }
      return users;   
   }
   
//   내가 추천한 사람
   public UserVO getMyRecommender() {
      String query = "SELECT USER_ID, USER_IDENTIFICATION, USER_NAME, USER_PASSWORD,"
            + " USER_PHONE, USER_NICKNAME, USER_EMAIL, USER_ADDRESS, USER_BIRTH, "
            + "USER_GENDER, USER_RECOMMENDER_ID FROM TBL_USER WHERE USER_ID = "
            + "("
            + "SELECT USER_RECOMMENDER_ID FROM TBL_USER WHERE USER_ID = ?"
            + ")";
      UserVO userVO = null;
      
      connection = DBConnecter.getConnection();
      try {
         preparedStatement = connection.prepareStatement(query);
         preparedStatement.setLong(1, userId);
         
         resultSet = preparedStatement.executeQuery();
         
         if(resultSet.next()) {
            userVO = new UserVO();
            userVO.setUserId(resultSet.getLong(1));
            userVO.setUserIdentification(resultSet.getString(2));
            userVO.setUserName(resultSet.getString(3));
            userVO.setUserPassword(resultSet.getString(4));
            userVO.setUserPhone(resultSet.getString(5));
            userVO.setUserNickname(resultSet.getString(6));
            userVO.setUserEmail(resultSet.getString(7));
            userVO.setUserAddress(resultSet.getString(8));
            userVO.setUserBirth(resultSet.getString(9));
            userVO.setUserGender(resultSet.getString(10));
            userVO.setUserRecommenderId(resultSet.getString(11));
         }
         
      } catch (SQLException e) {
         System.out.println("getMyRecommender() SQL문 오류");
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if(resultSet != null) {
               resultSet.close();
            }
            if(preparedStatement != null) {
               preparedStatement.close();
            }
            if(connection != null) {
               connection.close();
            }
         } catch (SQLException e) {
            throw new RuntimeException(e);
         }
      }
      return userVO;
   }
   
}



















