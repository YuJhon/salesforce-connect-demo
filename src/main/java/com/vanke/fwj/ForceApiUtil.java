package com.vanke.fwj;

import com.force.api.ApiConfig;
import com.force.api.ForceApi;
import com.force.api.QueryResult;
import com.force.api.ResourceRepresentation;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 *
 */
@Component
public class ForceApiUtil {

  private ForceApi api;

  @Value("${sf.client.id}")
  private String clientId;

  @Value("${sf.client.secret}")
  private String clientSecret;

  @Value("${sf.username}")
  private String username;

  @Value("${sf.password}")
  private String password;

  @Value("${sf.login.endpoint}")
  private String endpoint;

  @Value("${sf.version}")
  private String sfVersion;


  @PostConstruct
  public void init() {
    api = new ForceApi(new ApiConfig()
            .setUsername(username)
            .setPassword(password)
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .setLoginEndpoint(endpoint)
            .setApiVersionString(sfVersion));
  }

  private ForceApiUtil() {

  }


  /**
   * 直接调用apexRest接口，将返回值反序列化为clazz
   *
   * @param nextRecordsUrl
   * @param clazz
   * @return
   */
  public <T> QueryResult<T> apexRest(String nextRecordsUrl, Class<T> clazz) {
    return api.queryMore("/services/apexrest" + nextRecordsUrl, clazz);
  }

  /**
   * 直接调用apexRest接口，将返回值反序列化为Map
   *
   * @param nextRecordsUrl
   * @return
   */
  public QueryResult<Map> apexRest(String nextRecordsUrl) {
    return api.queryMore("/services/apexrest" + nextRecordsUrl);
  }

  /**
   * <pre>自定义的salesforce Post接口调用方式</pre>
   * @param path 接口路径
   * @param obj requestbody
   * @return
   */
  public Map<?, ?> apexRestPost(String path, Object obj) {
    ResourceRepresentation result = api.restPost(path, obj);
    if (null == result) {
      return Maps.newHashMap();
    }
    return result.asMap();
  }

  public ForceApi getInstance() {
    return api;
  }

}
