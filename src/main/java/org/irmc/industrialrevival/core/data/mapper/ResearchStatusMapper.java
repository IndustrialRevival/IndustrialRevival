package org.irmc.industrialrevival.core.data.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.jetbrains.annotations.Nullable;

@Mapper
public interface ResearchStatusMapper {
    @Insert("INSERT INTO research_status (username, researchStatusJson) VALUES (#{playerName}, #{researchStatus})")
    void insertResearchStatus(String playerName, String researchStatus);

    @Nullable @Select("SELECT * FROM research_status WHERE username = #{playerName}")
    String getResearchStatus(String playerName);
}
