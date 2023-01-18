package dao;

import vo.GirlVO;

public class GirlDAO {
   private String[] stringDatas = null;

   public GirlVO setObject(String s) {

      GirlVO vo = new GirlVO();

      stringDatas = s.split("\t");

      vo.setName(stringDatas[0]);
      vo.setRank(Integer.valueOf(stringDatas[1]));
      vo.setAmount(Integer.valueOf(stringDatas[2].replaceAll(",", "")));

      return vo;
   }
}