package dao;

import vo.BoyVO;

public class BoyDAO {
   
   private String[] stringDatas = null; 
   
   public BoyVO setObject(String s) {
      
      BoyVO vo = new BoyVO();
      
      stringDatas = s.split("\t");
      
      vo.setName(stringDatas[0]);
      vo.setRank(Integer.valueOf(stringDatas[1]));
      vo.setAmount(Integer.valueOf(stringDatas[2].replaceAll(",", "")));
      
      return vo;
   }
   
   
}