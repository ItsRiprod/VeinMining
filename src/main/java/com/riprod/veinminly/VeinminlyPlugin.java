package com.riprod.veinminly;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.util.Config;
import com.riprod.veinminly.commands.VeinMiningCommand;
import com.riprod.veinminly.config.VeinMiningConfig;
import com.riprod.veinminly.systems.VeinMiningInputSystem;
import com.riprod.veinminly.systems.VeinMiningSystem;

import java.util.logging.Level;

public class VeinminlyPlugin extends JavaPlugin {

    private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();
    private final Config<VeinMiningConfig> config;

    public VeinminlyPlugin(JavaPluginInit init) {
        super(init);
        this.config = this.withConfig("VeinMining", VeinMiningConfig.CODEC);
    }

    @Override
    protected void setup() {
        this.config.save();
        this.getCommandRegistry().registerCommand(new VeinMiningCommand(config));
        this.getEntityStoreRegistry().registerSystem(new VeinMiningSystem(config));
        this.getEntityStoreRegistry().registerSystem(new VeinMiningInputSystem(config));
        LOGGER.at(Level.INFO).log("VeinMining Plugin Loaded.");
    }
}