package ycraah.web.w2.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public enum MapperUtil {
  INSTANCE;

  private ModelMapper modelMapper;

  MapperUtil(){
    this.modelMapper = new ModelMapper();
    this.modelMapper.getConfiguration()
        .setFieldMatchingEnabled(true) //필드 이름이 일치할 때 객체 간 매핑 가능 활성화
        .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE) //객체의 private도 매핑 가능하도록 설정
        .setMatchingStrategy(MatchingStrategies.STRICT); //정확히 일치하는 필드만 매핑함
  }

  public ModelMapper getModelMapper() {
    return modelMapper;
  }
}
