package org.irmc.industrialrevival.core.data.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.jetbrains.annotations.Nullable;

@Mapper
public interface ResearchStatusMapper {
    @Insert("INSERT INTO research_status (username, researchStatusJson) VALUES (#{playerName}, #{researchStatusJson})")
    void insertResearchStatus(String playerName, String researchStatusJson);

    @Nullable @Select("SELECT * FROM research_status WHERE username = #{playerName}")
    String getResearchStatusJson(String playerName);
}
