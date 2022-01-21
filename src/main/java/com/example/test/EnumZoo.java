package com.example.test;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author: zhangyupeng
 * @date: 2019-10-30 11:30
 * @description: collection of enum class
 **/
public class EnumZoo {

  private static final Logger logger = LoggerFactory.getLogger(EnumZoo.class);

  public interface EnumInterface{
    String getName();
  }



  @JsonSerialize(using = EnumSerializer.class)
  @JsonDeserialize(using = Status.StatusDeserializer.class)
  public enum Status implements EnumInterface{
    /**
     * @description response status: success
     */
    SUCCESS("success",0, "成功"),
    /**
     * @description response status: failed
     */
    FAILED("failed",1,"失败");

    private String statusText;
    private Integer statusValue;
    private String message;

    Status(String statusText, Integer statusValue, String message){
      this.statusText = statusText;
      this.statusValue= statusValue;
      this.message = message;
    }


    @Override
    public String getName() {
      return this.statusText;
    }


    public static Status fromValue(Integer value) {
      if(value!=null){
        for(Status st: Status.values()){
          if(st.getStatusValue().equals(value)){
            return st;
          }
        }
      }
      return null;
    }

    public String getStatusText() {
      return statusText;
    }

    public Integer getStatusValue() {
      return statusValue;
    }

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }

    public static class StatusDeserializer extends JsonDeserializer<Status> {
      @Override
      public Status deserialize(JsonParser jsonParser,
          DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return Status.fromValue(jsonParser.getIntValue());
      }
    }
  }


  @JsonSerialize(using = EnumSerializer.class)
  @JsonDeserialize(using = Relation.RelationDeserializer.class)
  public enum Relation implements EnumInterface{
    /**
     * @description 逻辑关系，与
     */
    AND("and"),
    /**
     * @description 逻辑关系，或
     */
    OR("or"),
    /**
     * @description 逻辑关系，非
     */
    NOT("not");

    private String relationText;

    Relation(String relationText){
      this.relationText = relationText;
    }

    public String getRelationText() {
      return relationText;
    }

    @Override
    public String getName() {
      return this.relationText;
    }

    public static Relation fromName(String name){
      if(name!=null){
        for(Relation rl: Relation.values()){
          if(rl.getName().equals(name)){
            return rl;
          }
        }
      }
      return null;
    }

    public static class RelationDeserializer extends JsonDeserializer<Relation> {
      @Override
      public Relation deserialize(JsonParser jsonParser,
          DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return Relation.fromName(jsonParser.getValueAsString());
      }
    }

  }

  @JsonSerialize(using = EnumSerializer.class)
  @JsonDeserialize(using = UserIdType.UserIdTypeDeserializer.class)
  public enum UserIdType implements EnumInterface{
    /**
     * @description 用户输入id类型，手机号
     */
    MSISDN("msisdn"),
    /**
     * @description 用户输入id类型，imei
     */
    IMEI("imei"),
    /**
     * @description 用户输入id类型，imsi
     */
    IMSI("imsi"),
    /**
     * @description 用户输入id类型，微信id
     */
    WECHAT_UID("wechat_uid"),
    /**
     * @description 用户输入id类型，微信账号
     */
    WXID("wxid"),
    /**
     * @description 用户输入id类型，QQ号
     */
    QQ("qq_num"),
    /**
     * @description 用户输入id类型，微博账号
     */
    WEIBO("weibo_num"),
    /**
     * @description 微信id其他，这种类型的微信id在客户那里可能无法使用，所以与WECHAT_UID进行了区分
     */
    WECHAT_UID_OTHER("wechat_uid_other");




    private String idType;

    UserIdType(String idType){
      this.idType = idType;
    }

    public String getIdType() {
      return idType;
    }

    @Override
    public String getName() {
      return this.idType;
    }

    public static UserIdType fromName(String idType){
      if(idType!=null){
        for(UserIdType uit: UserIdType.values()){
          if(uit.getName().equals(idType)){
            return uit;
          }
        }
      }
      return null;
    }

    public static class UserIdTypeDeserializer extends JsonDeserializer<UserIdType> {
      @Override
      public UserIdType deserialize(JsonParser jsonParser,
          DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return UserIdType.fromName(jsonParser.getValueAsString());
      }
    }

  }


  @JsonSerialize(using = EnumSerializer.class)
  @JsonDeserialize(using = ApplicationType.ApplicationTypeDeserializer.class)
  public enum ApplicationType implements EnumInterface{
    /**
     * @description 应用类型，app
     */
    APP("app"),
    /**
     * @description 应用类型，网站
     */
    WEBSITE("website"),
    /**
     * @description 应用类型，翻墙软件
     */
    SS("ss"),
    /**
     * @description 应用类型，通联关系
     */
    COMMUNICATION("communication");

    




    private String applicationType;

    ApplicationType(String applicationType){
      this.applicationType = applicationType;
    }

    public String getApplicationType() {
      return applicationType;
    }

    @Override
    public String getName() {
      return this.applicationType;
    }

    public static ApplicationType fromName(String applicationType){
      if(applicationType!=null){
        for(ApplicationType at: ApplicationType.values()){
          if(at.getName().equals(applicationType)){
            return at;
          }
        }
      }
      return null;
    }

    public static class ApplicationTypeDeserializer extends JsonDeserializer<ApplicationType> {
      @Override
      public ApplicationType deserialize(JsonParser jsonParser,
          DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return ApplicationType.fromName(jsonParser.getValueAsString());
      }
    }

  }


  @JsonSerialize(using = EnumSerializer.class)
  @JsonDeserialize(using = UserRelationType.UserRelationTypeDeserializer.class)
  public enum UserRelationType implements EnumInterface{
    /**
     * @description 用户关系类型，好友关系
     */
    FRIEND("friend"),
    /**
     * @description  用户关系类型，伴随关系
     */
    FOLLOW("follow");


    private String userRelationType;

    UserRelationType(String userRelationType){
      this.userRelationType = userRelationType;
    }

    public String getUserRelationType() {
      return userRelationType;
    }

    @Override
    public String getName() {
      return this.userRelationType;
    }

    public static UserRelationType fromName(String userRelationType){
      if(userRelationType!=null){
        for(UserRelationType ur: UserRelationType.values()){
          if(ur.getName().equals(userRelationType)){
            return ur;
          }
        }
      }
      return null;
    }

    public static class UserRelationTypeDeserializer extends JsonDeserializer<UserRelationType> {
      @Override
      public UserRelationType deserialize(JsonParser jsonParser,
          DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return UserRelationType.fromName(jsonParser.getValueAsString());
      }
    }

  }


  @JsonSerialize(using = EnumSerializer.class)
  @JsonDeserialize(using = MovementType.MovementTypeDeserializer.class)
  public enum MovementType implements EnumInterface{
    /**
     * @description 人员流动方向，流入
     */
    INFLOW("inflow"),
    /**
     * @description 人员流动方向，流出
     */
    OUTFLOW("outflow"),
    /**
     * @description  人员流动方向，流入/流出
     */
    TWOWAYFLOW("twowayflow");


    private String movementType;

    MovementType(String movementType){
   this.movementType = movementType;
    }

    public String getMovementType() {
      return movementType;
    }

    @Override
    public String getName() {
      return this.movementType;
    }

    public static MovementType fromName(String movementType){
      if(movementType!=null){
        for(MovementType mt: MovementType.values()){
          if(mt.getName().equals(movementType)){
            return mt;
          }
        }
      }
      return null;
    }

    public static class MovementTypeDeserializer extends JsonDeserializer<MovementType> {
      @Override
      public MovementType deserialize(JsonParser jsonParser,
          DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return MovementType.fromName(jsonParser.getValueAsString());
      }
    }

  }


  @JsonSerialize(using = EnumSerializer.class)
  @JsonDeserialize(using = GeoType.GeoTypeDeserializer.class)
  public enum GeoType implements EnumInterface {
    /**
     * @description 地理位置类型，基站类型或精确GPS类型
     */
    ALL("all"),
    /**
     * @description 地理位置类型，基站类型
     */
    BS("bs"),
    /**
     * @description 地理位置类型，精确GPS类型
     */
    GPS("gps");




    private String geoType;

    GeoType(String geoType){
      this.geoType = geoType;
    }

    public String getGeoType() {
      return geoType;
    }

    @Override
    public String getName() {
      return this.geoType;
    }

    public static GeoType fromName(String geoType){
      if(geoType!=null){
        for(GeoType gt: GeoType.values()){
          if(gt.getName().equals(geoType)){
            return gt;
          }
        }
      }
      return null;
    }

    public static class GeoTypeDeserializer extends JsonDeserializer<GeoType> {
      @Override
      public GeoType deserialize(JsonParser jsonParser,
          DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return GeoType.fromName(jsonParser.getValueAsString());
      }
    }

  }



  @JsonSerialize(using = EnumSerializer.class)
  @JsonDeserialize(using = ExportType.ExportTypeDeserializer.class)
  public enum ExportType implements EnumInterface {
    /**
     * @description 浅层信息导出
     */
    NORMAL("normal"),
    /**
     * @description 深层信息导出
     */
    DEEP("deep");



    private String exportType;

    ExportType(String exportType){
      this.exportType = exportType;
    }

    public String getExportType() {
      return exportType;
    }

    @Override
    public String getName() {
      return this.exportType;
    }

    public static ExportType fromName(String exportType){
      if(exportType!=null){
        for(ExportType et: ExportType.values()){
          if(et.getName().equals(exportType)){
            return et;
          }
        }
      }
      return null;
    }

    public static class ExportTypeDeserializer extends JsonDeserializer<ExportType> {
      @Override
      public ExportType deserialize(JsonParser jsonParser,
          DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return ExportType.fromName(jsonParser.getValueAsString());
      }
    }

  }




  @JsonSerialize(using = EnumSerializer.class)
  @JsonDeserialize(using = SortType.SortTypeDeserializer.class)
  public enum SortType implements EnumInterface {
    /**
     * @description 升序
     */
    ASC("asc"),
    /**
     * @description 降序
     */
    DESC("desc");



    private String sortType;

    SortType(String sortType){
      this.sortType = sortType;
    }

    public String getSortType() {
      return sortType;
    }

    @Override
    public String getName() {
      return this.sortType;
    }

    public static SortType fromName(String sortType){
      if(sortType!=null){
        for(SortType st: SortType.values()){
          if(st.getName().equals(sortType)){
            return st;
          }
        }
      }
      return null;
    }

    public static class SortTypeDeserializer extends JsonDeserializer<SortType> {
      @Override
      public SortType deserialize(JsonParser jsonParser,
          DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return SortType.fromName(jsonParser.getValueAsString());
      }
    }

  }


}
