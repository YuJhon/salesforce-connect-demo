package com.vanke.fwj;

import com.force.api.QueryResult;
import com.force.api.ResourceRepresentation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SfConnectDemoApplicationTests {

  @Autowired
  ForceApiUtil forceApiUtil;

  @Test
  public void forceApiDemoTest() {
    String roomCode = null;
    try {
      roomCode = java.net.URLEncoder.encode("P0044-01-B0005-2单元-9-2-1002", "UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    String appId = "wxbc12b25a14f84de6";
    QueryResult<Map> queryMore = forceApiUtil.apexRest("/vksvc/room/QRcode?room_code="+roomCode+"&appId="+appId);
    List<Map> records2 = queryMore.getRecords();
    for (Map map : records2) {
      System.out.println(map.toString());
    }
    System.out.println("请求成功 。。。");
  }

  @Test
  public void roomListTest() {
    QueryResult<Map> queryMore = forceApiUtil.apexRest("/vksvc/room/list?OpenId=oJYDq1C0-1w_YMygLupVycmwykN8");
    List<Map> records2 = queryMore.getRecords();
    for (Map map : records2) {
      System.out.println(map.toString());
    }
    System.out.println("请求成功 。。。");
  }



  @Test
  public void forcePostTest() {
    OuterSPI outerSPI = new OuterSPI();
    outerSPI.setOuterSPI("gzvk");
    outerSPI.setMsgTemplate("");
    Map map = forceApiUtil.apexRestPost("/vksvc/push/noticeMsg",outerSPI);
    System.out.println(map);
    System.out.println("请求成功 。。。");
  }

}
