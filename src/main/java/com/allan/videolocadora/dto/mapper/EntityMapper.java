package com.allan.videolocadora.dto.mapper;

import com.allan.videolocadora.dto.*;
import com.allan.videolocadora.enumeration.ECategory;
import com.allan.videolocadora.enumeration.EItemType;
import com.allan.videolocadora.enumeration.ESex;
import com.allan.videolocadora.enumeration.EStatus;
import com.allan.videolocadora.enumeration.converter.ECategoryConverter;
import com.allan.videolocadora.enumeration.converter.EItemTypeConverter;
import com.allan.videolocadora.enumeration.converter.ESexConverter;
import com.allan.videolocadora.enumeration.converter.EStatusConverter;
import com.allan.videolocadora.model.*;
import com.allan.videolocadora.model.Class;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;

@Mapper(componentModel = "spring")
public interface EntityMapper {
    //ACTOR
    ActorDTO toActorDTO(Actor actor);

    Actor toActorEntity(ActorDTO dto);

    //CLASS
    ClassDTO toClassDTO(Class c);

    Class toClassEntity(ClassDTO dto);

    //DIRECTOR
    DirectorDTO toDirectorDTO(Director director);

    Director toDirectorEntity(DirectorDTO dto);

    //ITEM
    ItemDTO toItemDTO(Item item);

    Item toItemEntity(ItemDTO dto);

    //MOVIE
    MovieDTO toMovieDTO(Movie movie);

    Movie toMovieEntity(MovieDTO movie);

    //PARTNER
    PartnerDTO toPartnerDTO(Partner partner);

    Partner toPartnerEntity(PartnerDTO partnerDTO);

    //DEPENDENT
    DependentDTO toDependentDTO(Dependent dependent);

    Dependent toDependentEntity(DependentDTO dependentDTO);

    //ENUMS
    @ValueMapping(source = "THRILLER", target = "Thriller")
    @ValueMapping(source = "ROMANCE", target = "Romance")
    @ValueMapping(source = "HORROR", target = "Horror")
    @ValueMapping(source = "COMEDY", target = "Comedy")
    default ECategory getCategoryEnumValue(String category) {
        return new ECategoryConverter().convertToEntityAttribute(category);
    }

    @ValueMapping(source = "DVD", target = "DVD")
    @ValueMapping(source = "TAPE", target = "Tape")
    @ValueMapping(source = "BLURAY", target = "Blu-ray")
    default EItemType getItemTypeEnumValue(String itemType) {
        return new EItemTypeConverter().convertToEntityAttribute(itemType);
    }

    @ValueMapping(source = "MALE", target = "Male")
    @ValueMapping(source = "FEMALE", target = "Female")
    @ValueMapping(source = "UNDEFINED", target = "Undefined")
    default ESex getSexEnumValue(String sex) {
        return new ESexConverter().convertToEntityAttribute(sex);
    }

    @ValueMapping(source = "ACTIVE", target = "Active")
    @ValueMapping(source = "INACTIVE", target = "Inactive")
    default EStatus getStatusEnumValue(String status) {
        return new EStatusConverter().convertToEntityAttribute(status);
    }

    @ValueMapping(source = "THRILLER", target = "Thriller")
    @ValueMapping(source = "ROMANCE", target = "Romance")
    @ValueMapping(source = "HORROR", target = "Horror")
    @ValueMapping(source = "COMEDY", target = "Comedy")
    default String getCategoryValue(ECategory eCategory) {
        return new ECategoryConverter().convertToDatabaseColumn(eCategory);
    }

    @ValueMapping(source = "DVD", target = "DVD")
    @ValueMapping(source = "TAPE", target = "Tape")
    @ValueMapping(source = "BLURAY", target = "Blu-ray")
    default String getItemTypeValue(EItemType eItemType) {
        return new EItemTypeConverter().convertToDatabaseColumn(eItemType);
    }

    @ValueMapping(source = "MALE", target = "Male")
    @ValueMapping(source = "FEMALE", target = "Female")
    @ValueMapping(source = "UNDEFINED", target = "Undefined")
    default String getSexValue(ESex eSex) {
        return new ESexConverter().convertToDatabaseColumn(eSex);
    }

    @ValueMapping(source = "ACTIVE", target = "Active")
    @ValueMapping(source = "INACTIVE", target = "Inactive")
    default String getStatusValue(EStatus eStatus) {
        return new EStatusConverter().convertToDatabaseColumn(eStatus);
    }
}
