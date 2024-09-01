package org.irmc.industrialrevival.core.data.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.irmc.industrialrevival.core.guide.GuideSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Mapper
public interface GuideSettingsMapper {
    @Insert(
            "INSERT INTO guide_settings (username, fireWorksEnabled, learningAnimationEnabled, language) "
                    + "VALUES (#{username}, #{settings.fireWorksEnabled}, #{settings.learningAnimationEnabled}, #{settings.language})")
    void save(@NotNull String username, @NotNull @Param("settings") GuideSettings settings);

    @Nullable
    @Select(
            "SELECT fireWorksEnabled, learningAnimationEnabled, language FROM guide_settings WHERE username = #{username}")
    GuideSettings get(@NotNull @Param("username") String username);
}
