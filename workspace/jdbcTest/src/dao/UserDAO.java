package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import domain.UserVO;

public class UserDAO {
	public Connection connection;
	public PreparedStatement preparedStatement;
	public ResultSet resultSet;
	//아이디 중복검사
	public boolean checkId(String userIdentification) {
		String query = 
				"SELECT COUNT(USER_ID) FROM TBL_USER WHERE USER_IDENTIFICATION =? ";
		boolean result = false;
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userIdentification);
			resultSet = preparedStatement.executeQuery();
			
			resultSet.next();
			result = resultSet.getInt(1) == 0;
		} catch (SQLException e) {
			System.out.println("checkId(String) SQL문 오류");
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
		return result;
	}
	public void join(UserVO vo) {
		String query = "INSERT INTO TBL_USER "
				+ "(USER_ID, USER_IDENTIFICATION, USER_NAME, USER_PASSWORD, USER_PHONE"
				+ "USER_NICKNAME, USER_EMAIL, USER_ADDRESS, USER_BIRTH, USER_GENDER, USER_RECOMMENDER_ID) "
				+ "VALUES(SEQ_USER.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		connection = DBConnecter.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, vo.getUserIdentification());
			preparedStatement.setString(2, vo.getUserName());
			preparedStatement.setString(3, vo.getUserPassword());
			preparedStatement.setString(4, vo.getUserPhone());
			preparedStatement.setString(5, vo.getUserNickname());
			preparedStatement.setString(6, vo.getUserEmail());
			preparedStatement.setString(7, vo.getUserAddress());
			preparedStatement.setString(8, vo.getUserBirth());
			preparedStatement.setString(9, vo.getUserGender());
			preparedStatement.setString(10, vo.getUserRecommenderId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("join(UserVO) SQL문 오류");
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
//	로그인
	public boolean login(String userIdentification, String userPassword) {
		String query = "SELCET USER_ID FROM TBL_USER WHERE USER_IDENTIFICATION =? AND USER_PASSWORD =? ";
		boolean check = false;
		UserVO vo = new UserVO();
		connection = DBConnecter.getConnection();
		try {
			connection.prepareStatement(query);
			preparedStatement.setString(1, userIdentification);
			preparedStatement.setString(2, userPassword);
			preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				userId = resultSet.getLong(1);
				check = true;
			}
		} catch (SQLException e) {
			System.out.println();
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
		
	}
	
}