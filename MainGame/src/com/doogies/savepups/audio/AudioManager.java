package com.doogies.savepups.audio;

public class AudioManager {

    // Audio
    public static AudioPlayer ogreAttack = new AudioPlayer();

    public static AudioPlayer orcAttack = new AudioPlayer();

    public static AudioPlayer goldCoinDrop = new AudioPlayer();

    public static AudioPlayer barking1 = new AudioPlayer();

    public AudioManager() {

        ogreAttack.setFile("/soundEffects/rpgSounds/NPC/ogre/ogreAttack");
        orcAttack.setFile("/soundEffects/rpgSounds/NPC/gutteral beast/mnstr11");
        goldCoinDrop.setFile("/soundEffects/rpgSounds/inventory/coin2");

        barking1.setFile("/soundEffects/rubberduck/barking_01");
    }


}
