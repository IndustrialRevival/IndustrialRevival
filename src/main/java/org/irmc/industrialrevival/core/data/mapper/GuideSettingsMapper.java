package org.irmc.industrialrevival.core.data.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.irmc.industrialrevival.core.guide.GuideSettings;

public interface GuideSettingsMapper {
    @Insert(""" 
               if exists  (select guide_settings from username where username = #{username})
                  insert into guide_settings (username, guide_settings) values (#{username}, #{guideSettings})
               else
                  update username set guide_settings = #{guideSettings} where username = #{username}""")
    void save(String username, GuideSettings guideSettings);

    @Select("select * from guide_settings where username = #{username}")
    GuideSettings get(String username);
}
