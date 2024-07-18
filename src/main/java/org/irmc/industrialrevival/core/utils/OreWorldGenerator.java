package org.irmc.industrialrevival.core.utils;

import java.util.Random;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;

public class OreWorldGenerator extends ChunkGenerator {
  @Override
  public void generateNoise(
      @NotNull WorldInfo worldInfo,
      @NotNull Random random,
      int chunkX,
      int chunkZ,
      @NotNull ChunkData chunkData) {}
}
