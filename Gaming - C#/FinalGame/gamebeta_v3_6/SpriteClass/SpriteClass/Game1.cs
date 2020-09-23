using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Audio;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.GamerServices;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using Microsoft.Xna.Framework.Media;
//game version 3.6 updating 7/30 (only needs updated instructions for intro)
//updated running to have a "stamina" (running uses stamaina; when stamina runs out, player can't run)
//TODO: make running stamina bar
//final
namespace SpriteClass
{
    /// <summary>
    /// This is the main type for your game
    /// </summary>
    /// 

    public class Game1 : Microsoft.Xna.Framework.Game
    {
        GraphicsDeviceManager graphics;
        SpriteBatch spriteBatch;
        //fireball projectile
        Texture2D projectile_textile;
        PlayerProjectile projectiles;
        List<PlayerProjectile> fireball_list = new List<PlayerProjectile>();
        Point fireball_collision_offset = new Point(5, 20);

        KeyboardState old_ks = Keyboard.GetState();
        GamePadState old_gs = GamePad.GetState(PlayerIndex.One);
        
        SoundEffect laser;

        //game window
        Rectangle game_window;
        Texture2D classroom_background;
        Color background_color = Color.White;
        //main character chrono
        Texture2D player1_texture;
        Player1 chrono;
        int chrono_ms_per_frame = 200;
        Point chrono_collision_offset = new Point(60, 40);
        //knife playerprojectile
        Texture2D knife_texture;
        PlayerProjectile knife_object;
        List<PlayerProjectile> knife_list = new List<PlayerProjectile>();
        Point knife_collision_offset = new Point(10, 10);
        //audio
        //TODO: add songs
        Song intro_music;
        Song gameplay_music;
        Song won_game_music;
        Song boss_music;
        public static Song lose_game_music;
        //TODO: SoundEffect variables
        SoundEffect chrono_hit_sound;
        SoundEffect item_fireball_sound;
        SoundEffect item_health_sound;
        SoundEffect item_knife_sound;
        SoundEffect knife_throw_sound;
        SoundEffect slime_sound;
        SoundEffect zombie_hit_sound;
        SoundEffect zombie_spawn_sound;
        SoundEffect window_crashing_sound;
        SoundEffect boss_spawn_sound;
        SoundEffect won_sound;
        SoundEffect lose_sound;

        public static Boolean lost_game = false;

        //font variables
        SpriteFont projectile_font;
        Vector2 projectile_font_position = Vector2.Zero;
        Color fireball_font_color = Color.Red;
        String fireball_shout1 = "FIREBALL!!";
        Color knife_font_color = Color.Silver;
        String knife_shout1 = "KNIFE ATTACK!!";
        //intro variables
        Boolean is_intro = true;
        Boolean showing_instructions = false;
        Texture2D intro_background;
        Texture2D howtoplay;
        Texture2D howtobutton;
        Texture2D intro_press_start;
        SoundEffect start_effect;

        //zombie variables
        Texture2D zombie1_texture;
        SpriteFont title_font;
        List<ChasingEnemy> zombie_list = new List<ChasingEnemy>();
        int zombie_spawn_timer = 0;
        int zombie_next_spawn = 4000;
        Random rng = new Random();
        int enemy_attack_timer = 0;
        int enemy_attack_speed = 1000;

        //fireball ammo
        Texture2D fireball_ammo_texture;
        int fireball_ammo_spawn_timer = 0;
        int fireball_ammo_next_spawn = 8000;
        List<HealthItem> fireball_ammo_list = new List<HealthItem>();
        //knife ammo
        Texture2D knife_ammo_texture;
        int knife_ammo_spawn_timer = 0;
        int knife_ammo_next_spawn = 9000;
        List<HealthItem> knife_ammo_list = new List<HealthItem>();
        //health item
        Texture2D hamburger_texture;
        int hamburger_spawn_timer = 0;
        int hamburger_next_spawn = 10000;
        List<HealthItem> hamburger_list = new List<HealthItem>();
        //health bar
        Texture2D healthbar_texture;
        HealthBar healthbar;
        //boss health bar
        Texture2D boss_healthbar_texture;
        HealthBar boss_healthbar;
        //stamina bar
        Texture2D staminabar_texture;
        StaminaBar staminabar;

        //points
        int score = 0;
        double minute_time;
        double seconds_time;
        Boolean won_game = false;
        int generic_zombie_kills = 0;
        //HUD added
        Texture2D sword_texture;
        Texture2D fireball_texture;
        //screens
        Texture2D lost_bg_texture;
        Texture2D won_bg_texture;
        //Boss variables
        Boss1 boss;
        Boolean boss1_spawned = false;
        Texture2D boss_texture;

        ChasingSlime slime;
        Texture2D slime_texture;
        List<ChasingSlime> slime_list = new List<ChasingSlime>();
        int slime_spawn_timer = 0;
        int slime_next_spawn = 5000;

        SpriteFont stat_font;

        public Game1()
        {
            graphics = new GraphicsDeviceManager(this);
            Content.RootDirectory = "Content";

            graphics.IsFullScreen = true;

            graphics.PreferredBackBufferWidth = 1280;
            graphics.PreferredBackBufferHeight = 720;
        }

        /// <summary>
        /// Allows the game to perform any initialization it needs to before starting to run.
        /// This is where it can query for any required services and load any non-graphic
        /// related content.  Calling base.Initialize will enumerate through any components
        /// and initialize them as well.
        /// </summary>
        protected override void Initialize()
        {
            // TODO: Add your initialization logic here
            IsMouseVisible = false;
            Window.Title = "Chronicles of Academia Finals: Surviving College";

            game_window = new Rectangle(0, 0, Window.ClientBounds.Width, Window.ClientBounds.Height);
            base.Initialize();
        }

        /// <summary>
        /// LoadContent will be called once per game and is the place to load
        /// all of your content.
        /// </summary>
        /// 

        protected override void LoadContent()
        {
            // Create a new SpriteBatch, which can be used to draw textures.
            spriteBatch = new SpriteBatch(GraphicsDevice);

            // TODO: use this.Content to load your game content here
            projectile_textile = Content.Load<Texture2D>("sprites/fireball_0");
            classroom_background = Content.Load<Texture2D>("sprites/bg");
            player1_texture = Content.Load<Texture2D>("sprites/chrono_walkingsheet_ver4");
            knife_texture = Content.Load<Texture2D>("sprites/knife_sheet_ver4");
            laser = Content.Load<SoundEffect>(@"audio/projectile");

            chrono = new Player1(player1_texture, 
                                new Vector2(Window.ClientBounds.Width/2 - 68, Window.ClientBounds.Height/2 - 68),
                                new Point(135, 135), chrono_collision_offset,
                                Point.Zero, new Point(6, 5),
                                new Vector2(4, 4), chrono_ms_per_frame);
            //load audio and play at start
            gameplay_music = Content.Load<Song>("audio/8 Bit Eye of the Tiger - Survivor");
            intro_music = Content.Load<Song>("audio/finalcut");
            MediaPlayer.Play(intro_music);
            MediaPlayer.IsRepeating = true;
            //load font texts
            projectile_font = Content.Load<SpriteFont>("sprites/SpriteFont1");
            stat_font = Content.Load<SpriteFont>("sprites/StatFont");
            //load intro graphics
            intro_background = Content.Load<Texture2D>("sprites/crest");
            intro_press_start = Content.Load<Texture2D>("sprites/enter");
            howtobutton = Content.Load<Texture2D>("sprites/howto");
            howtoplay = Content.Load<Texture2D>("sprites/howtoplay");
            start_effect = Content.Load<SoundEffect>("audio/start_sound");

            title_font = Content.Load<SpriteFont>("sprites/intro_title");
            hamburger_texture = Content.Load<Texture2D>("sprites/hamburger sprite");
            zombie1_texture = Content.Load<Texture2D>("sprites/newzombie");
            //boss health bar
            boss_healthbar_texture = Content.Load<Texture2D>("sprites/BossBar3");

            //hud added
            sword_texture = Content.Load<Texture2D>("sprites/sword");
            fireball_texture = Content.Load<Texture2D>("sprites/ammo_ball");
            //fireball and knife items
            fireball_ammo_texture = Content.Load<Texture2D>("sprites/coffee");
            knife_ammo_texture = Content.Load<Texture2D>("sprites/knifeitem");  
            //screens
            lost_bg_texture = Content.Load<Texture2D>("sprites/gameover_bg");
            won_bg_texture = Content.Load<Texture2D>("sprites/winscreen");
            //boss texture
            boss_texture = Content.Load<Texture2D>("sprites/bahamut");
            slime_texture = Content.Load<Texture2D>("sprites/greenslime");
            //win music
            won_game_music = Content.Load<Song>("audio/winmusic");
            boss_music = Content.Load<Song>("audio/bossmusic");
            lose_game_music = Content.Load<Song>("audio/theend");
            //soundeffects
            chrono_hit_sound = Content.Load<SoundEffect>("audio/chronoHitSound2");
            item_fireball_sound = Content.Load<SoundEffect>("audio/ItemFireballSound");
            item_health_sound = Content.Load<SoundEffect>("audio/ItemHealthSound");
            item_knife_sound = Content.Load<SoundEffect>("audio/ItemKnifeSound");
            boss_spawn_sound = Content.Load<SoundEffect>("audio/boss_spawn_sound");
            knife_throw_sound = Content.Load<SoundEffect>("audio/knifeThrowSound");
            slime_sound = Content.Load<SoundEffect>("audio/Slime_sound");
            window_crashing_sound = Content.Load<SoundEffect>("audio/Window_Crashing");
            zombie_hit_sound = Content.Load<SoundEffect>("audio/Zombie_Hit_Sound");
            zombie_spawn_sound = Content.Load<SoundEffect>("audio/zombie_spawn_sound");
            won_sound = Content.Load<SoundEffect>("audio/win_soundfx");
            lose_sound = Content.Load<SoundEffect>("audio/lose_soundfx");
            //stamina bar
            staminabar_texture = Content.Load<Texture2D>("sprites/stamina_bar_sheet");
            staminabar = new StaminaBar(staminabar_texture, new Vector2(Window.ClientBounds.Width / 6, 0),
                                            new Point(37, 36), Point.Zero, Point.Zero, 
                                            new Point(1, 9), Vector2.Zero, 0, chrono);
            //health bar
            healthbar_texture = Content.Load<Texture2D>("sprites/healthbar");
            healthbar = new HealthBar(healthbar_texture, Vector2.Zero, new Point(200, 36), Point.Zero,
                                        Point.Zero, new Point(1, 1), Vector2.Zero, 0, chrono);

        }

        /// <summary>
        /// UnloadContent will be called once per game and is the place to unload
        /// all content.
        /// </summary>
        protected override void UnloadContent()
        {
            // TODO: Unload any non ContentManager content here
        }

        /// <summary>
        /// Allows the game to run logic such as updating the world,
        /// checking for collisions, gathering input, and playing audio.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        /// 

        ///HELPER METHODS///
        ///////////////////////////////////////////////////////
        //reset method
        protected void ResetGame()
        {
            //reset player and healthbar
            chrono = new Player1(player1_texture, 
                                new Vector2(Window.ClientBounds.Width / 2 - 68, Window.ClientBounds.Height / 2 - 68),
                                new Point(135, 135), chrono_collision_offset,
                                Point.Zero, new Point(6, 5),
                                new Vector2(4, 4), chrono_ms_per_frame);
            healthbar = new HealthBar(healthbar_texture, Vector2.Zero, new Point(200, 36), Point.Zero,
                                        Point.Zero, new Point(1, 1), Vector2.Zero, 0, chrono);
            staminabar = new StaminaBar(staminabar_texture, new Vector2(Window.ClientBounds.Width / 6, 0),
                                            new Point(37, 36), Point.Zero, Point.Zero,
                                            new Point(1, 9), Vector2.Zero, 0, chrono);
            //reset music
            MediaPlayer.Stop();
            MediaPlayer.Play(gameplay_music);
            //reset booleans
            lost_game = false;
            boss1_spawned = false;
            won_game = false;
            //reset kills and sore
            generic_zombie_kills = 0;
            score = 0;
            //reset timers
            slime_spawn_timer = 0;
            hamburger_spawn_timer = 0;
            knife_ammo_spawn_timer = 0;
            fireball_ammo_spawn_timer = 0;
            zombie_spawn_timer = 0;
            enemy_attack_timer = 0;
            zombie_next_spawn = 4000;
            //clear all Lists
            zombie_list.Clear();
            fireball_ammo_list.Clear();
            knife_ammo_list.Clear();
            hamburger_list.Clear();
            slime_list.Clear();
            knife_list.Clear();
            fireball_list.Clear();
        }

        //hamburger timed spawn
        protected void HamburgerTimedSpawn(GameTime gameTime)
        {
            hamburger_spawn_timer += gameTime.ElapsedGameTime.Milliseconds;
            if (hamburger_spawn_timer >= hamburger_next_spawn)
            {
                if (hamburger_list.Count < 2)
                {
                    Point frameSize = new Point(35, 38);
                    HealthItem hi = new HealthItem(hamburger_texture,
                                                    new Vector2(rng.Next(Window.ClientBounds.Width - frameSize.X),
                                                        rng.Next(200, Window.ClientBounds.Height - frameSize.Y - 15)),
                                                    frameSize, new Point(2, 5), Point.Zero, new Point(6, 1),
                                                    Vector2.Zero, 150, chrono, 30);
                    hamburger_list.Add(hi);
                }
                hamburger_spawn_timer = 0;
            }
        }
        //fireball and knife items spawn here
        protected void FireballTimedSpawn(GameTime gameTime)
        {
            fireball_ammo_spawn_timer += gameTime.ElapsedGameTime.Milliseconds;
            if (fireball_ammo_spawn_timer >= fireball_ammo_next_spawn)
            {
                if (fireball_ammo_list.Count < 3)
                {
                    Point frameSize = new Point(29, 31);
                    HealthItem f = new HealthItem(fireball_ammo_texture,
                                                    new Vector2(rng.Next(Window.ClientBounds.Width - frameSize.X),
                                                        rng.Next(200, Window.ClientBounds.Height - frameSize.Y - 15)),
                                                    frameSize, Point.Zero, Point.Zero, new Point(3, 1),
                                                    Vector2.Zero, 300, chrono, 30);
                    fireball_ammo_list.Add(f);
                }
                fireball_ammo_spawn_timer = 0;
            }

        }
        protected void KnifeTimedSpawn(GameTime gameTime)
        {
            knife_ammo_spawn_timer += gameTime.ElapsedGameTime.Milliseconds;
            if (knife_ammo_spawn_timer >= knife_ammo_next_spawn)
            {
                if (knife_ammo_list.Count < 2)
                {
                    Point frameSize = new Point(32, 32);
                    HealthItem k = new HealthItem(knife_ammo_texture,
                                                    new Vector2(rng.Next(Window.ClientBounds.Width - frameSize.X),
                                                        rng.Next(200, Window.ClientBounds.Height - frameSize.Y - 15)),
                                                    frameSize, Point.Zero, Point.Zero, new Point(8, 1),
                                                    Vector2.Zero, 300, chrono, 30);
                    knife_ammo_list.Add(k);
                }
                knife_ammo_spawn_timer = 0;
            }
        }
        //zombies created here
        protected void ZombieTimedSpawn(GameTime gameTime)
        {
            zombie_spawn_timer += gameTime.ElapsedGameTime.Milliseconds;
            if (zombie_spawn_timer >= zombie_next_spawn)
            {
                if (zombie_list.Count <= 6)
                {
                    if (!zombie_spawn_sound.Play())
                        {
                            zombie_spawn_sound.Play();   
                        }
                    if (!window_crashing_sound.Play())
                        {
                            window_crashing_sound.Play();  
                        }
                    Point frameSize = new Point(60, 84);
                    Vector2 position = this.RandomEnemySpawnPosition(frameSize);
                    float random_speed_XY = rng.Next(2, 5);
                    Vector2 random_speed = new Vector2(random_speed_XY, random_speed_XY);
                    float default_MSPerFrame = 200;
                    float temp = (default_MSPerFrame * 2) / random_speed_XY;
                    int zombie_frame_rate = (int)temp;
                    ChasingEnemy z = new ChasingEnemy(zombie1_texture, position,
                                                        frameSize, new Point(3, 3), Point.Zero,
                                                        new Point(4, 5), random_speed,
                                                        200, chrono, 100, 5);
                    zombie_list.Add(z);
                    //spawn 2 zombies when boss is not out
                    if (!boss1_spawned)
                    {
                        if (zombie_list.Count <= 6)
                        {
                            position = this.RandomEnemySpawnPosition(frameSize);
                            random_speed_XY = rng.Next(2, 5);
                            random_speed = new Vector2(random_speed_XY, random_speed_XY);
                            temp = (default_MSPerFrame * 2) / random_speed_XY;
                            zombie_frame_rate = (int)temp;
                            ChasingEnemy z2 = new ChasingEnemy(zombie1_texture, position,
                                                                frameSize, new Point(3, 3), Point.Zero,
                                                                new Point(4, 5), random_speed,
                                                                200, chrono, 100, 5);
                            zombie_list.Add(z2);
                        }
                    }

                    zombie_spawn_timer = 0;
                }
            }
        }
        //creates random spawn locations
        protected Vector2 RandomEnemySpawnPosition(Point frameSize)
        {
            Vector2 position = Vector2.Zero;
            
            switch (rng.Next(3))
            {
                case 0:  //left to right
                    position = new Vector2(-frameSize.X,
                                            rng.Next(Window.ClientBounds.Height));
                    break;

                case 1: //right to left
                    position = new Vector2(Window.ClientBounds.Width,
                                            rng.Next(Window.ClientBounds.Height));
                    break;

                case 2: //bottom to top
                    position = new Vector2(Window.ClientBounds.Width/2 - frameSize.X/2,
                                            Window.ClientBounds.Height);
                    break;
            }

            return position;
        }

        //slime collision
        public void SlimeCollision()
        {
            for (int subscript = 0; subscript < slime_list.Count; subscript++)
            {
                ChasingSlime current_slime = slime_list[subscript];
                //DoesSlimeIntersectZombie(current_slime, subscript);
                for (int counter = 0; counter < zombie_list.Count; counter++)
                {
                    ChasingEnemy current_zombie = zombie_list[counter];
                    if (current_slime.getBoundingBox().Intersects(current_zombie.getBoundingBox()))
                    {
                        //soundeffect
                        if (!slime_sound.Play())
                        {
                            slime_sound.Play();
                        }
                        zombie_list.RemoveAt(counter);
                        counter--;
                        slime_list.RemoveAt(subscript);  //gave error
                        if (subscript > 0)
                            subscript--;
                        score += 10;
                        return;
                    }
                }
            }
            for (int sub = 0; sub < slime_list.Count; sub++)
            {
                ChasingSlime current_slime = slime_list[sub];
                if (current_slime.getBoundingBox().Intersects(chrono.getBoundingBox()))
                {
                    if (!slime_sound.Play())
                        {
                            slime_sound.Play();
                        }
                    chrono.isHit(current_slime.getDamage());
                    slime_list.RemoveAt(sub);
                    sub--;
                }
            }
        }


        protected override void Update(GameTime gameTime)
        {
            // Allows the game to exit
            if (GamePad.GetState(PlayerIndex.One).Buttons.Back == ButtonState.Pressed)
                this.Exit();

            // TODO: Add your update logic here
            KeyboardState ks = Keyboard.GetState();
            //pressing escape exits the game
            if (ks.IsKeyDown(Keys.Escape))
                this.Exit();
            MouseState ms = Mouse.GetState();
            GamePadState gs = GamePad.GetState(PlayerIndex.One);

            //start update
            if (is_intro)
            {
                if (old_ks.IsKeyUp(Keys.Enter) && ks.IsKeyDown(Keys.Enter) || gs.Buttons.Start== ButtonState.Pressed && old_gs.Buttons.Start == ButtonState.Released)
                {
                    start_effect.Play();
                    is_intro = false;
                    MediaPlayer.Stop();
                    MediaPlayer.Play(gameplay_music);
                }
                if (old_ks.IsKeyUp(Keys.H) && ks.IsKeyDown(Keys.H) || gs.Buttons.Y == ButtonState.Pressed && old_gs.Buttons.Y == ButtonState.Released)
                {
                    showing_instructions = !showing_instructions;
                }
            }
            else if (won_game)
            {
                //TODO: 
                //reset game if spacebar is pressed
                if (old_ks.IsKeyUp(Keys.Space) && ks.IsKeyDown(Keys.Space) || gs.Buttons.X == ButtonState.Pressed && old_gs.Buttons.X == ButtonState.Released)
                {
                    this.ResetGame();
                }
            }
            else if (!lost_game)
            {
                if (old_ks.IsKeyUp(Keys.Enter) && ks.IsKeyDown(Keys.Enter) || gs.Buttons.Start == ButtonState.Pressed && old_gs.Buttons.Start == ButtonState.Released)
                {
                    start_effect.Play();
                    is_intro = true;
                }
                //player1 update
                chrono.Update(gameTime, Window.ClientBounds);
                //sets when fireball is created
                
                if (ks.IsKeyDown(Keys.L) && old_ks.IsKeyUp(Keys.L) || gs.Buttons.A == ButtonState.Pressed && old_gs.Buttons.A == ButtonState.Released)
                {
                    if (chrono.getFireballAmmo() > 0)
                    {
                        projectiles = new PlayerProjectile(projectile_textile, chrono.getPosition(),
                                            new Point(64, 64), fireball_collision_offset,
                                            Point.Zero, new Point(8, 8),
                                            new Vector2(8, 8), 50, chrono, 25);
                        fireball_list.Add(projectiles);
                        chrono.addFireballAmmo(-1);
                        laser.Play();
                    }
                }
                //sets when knife is created
                if (ks.IsKeyDown(Keys.K) && old_ks.IsKeyUp(Keys.K) || gs.Buttons.B == ButtonState.Pressed && old_gs.Buttons.B == ButtonState.Released)
                {
                    if (chrono.getKnifeAmmo() > 0)
                    {
                        knife_object = new PlayerProjectile(knife_texture, chrono.getPosition(),
                                            new Point(64, 64), knife_collision_offset,
                                            Point.Zero, new Point(8, 8),
                                            new Vector2(8, 8), 50, chrono, 50);
                        knife_list.Add(knife_object);
                        chrono.addKnifeAmmo(-1);
                        knife_throw_sound .Play();
                    }
                }

                //this is a foreach loop
                //this line visits all elements in fireball_list and knife_list
                foreach (Sprite current in fireball_list)
                {
                    current.Update(gameTime, Window.ClientBounds);
                }
                foreach (Sprite current in knife_list)
                {
                    current.Update(gameTime, Window.ClientBounds);
                }

                //find any projectiles that are out of bounds, then remove them
                for (int subscript = 0; subscript < fireball_list.Count; subscript++)
                {
                    Projectile current = fireball_list[subscript];
                    if (current.isOutOfBounds())
                    {
                        fireball_list.RemoveAt(subscript);
                        //when deleting, all subsequent objects are moved over by 1
                        //so, we decrement the subscript to check the object that was moved
                        subscript--;
                    }
                }
                for (int subscript = 0; subscript < knife_list.Count; subscript++)
                {
                    Projectile current = knife_list[subscript];
                    if (current.isOutOfBounds())
                    {
                        knife_list.RemoveAt(subscript);
                        //when deleting, all subsequent objects are moved over by 1
                        //so, we decrement the subscript to check the object that was moved
                        subscript--;
                    }
                }

                //sets zombie logic
                this.ZombieTimedSpawn(gameTime);
                foreach (ChasingEnemy current in zombie_list)
                {
                    current.Update(gameTime, Window.ClientBounds);
                }
                for (int subscript = 0; subscript < zombie_list.Count; subscript++)
                {
                    ChasingEnemy current_zombie = zombie_list[subscript];
                    //zombie damage/kill player(chrono)
                    if (current_zombie.getBoundingBox().Intersects(chrono.getBoundingBox()))
                    {
                        //GamePad.SetVibration(PlayerIndex.One, 1.0f, 5.0f);
                        //do damage immediately on contact (first hit)
                        if (current_zombie.isFirstHit())
                        {
                            if (!chrono_hit_sound.Play())
                            {
                                chrono_hit_sound.Play();
                            }
                            //turn the motor: left low frequency, right high frequency
                            //GamePad.SetVibration(PlayerIndex.One, 1.0f, 5.0f);
                            chrono.isHit(current_zombie.getDamage());
                            chrono.setBeingHit(true);
                            current_zombie.setFirstHit(false);
                        }
                        //after first hit, only damage periodically based on attack speed
                        else
                        {
                            //wait 1 second (1000 ms) before damaging again
                            enemy_attack_timer += gameTime.ElapsedGameTime.Milliseconds;
                            if (enemy_attack_timer > enemy_attack_speed)
                            {
                                if (!chrono_hit_sound.Play())
                                {
                                    chrono_hit_sound.Play();
                                }
                                
                                chrono.isHit(current_zombie.getDamage());
                                chrono.setBeingHit(true);
                                enemy_attack_timer = 0;
                            }
                        }

                        //
                    }
                    //if zombie has not yet hit player, next hit will be first
                    else
                    {
                        //GamePad.SetVibration(PlayerIndex.One, 0f, 0f);
                        current_zombie.setFirstHit(true);
                        chrono.setBeingHit(false);
                    }

                    //fireball damage/kill zombie
                    for (int sub = 0; sub < fireball_list.Count; sub++)
                    {
                        PlayerProjectile current_fireball = fireball_list[sub];
                        if (current_fireball.getBoundingBox().Intersects(current_zombie.getBoundingBox()))
                        {
                            if (!zombie_hit_sound.Play())
                            {
                                zombie_hit_sound.Play();
                            }
                            current_zombie.isHit(current_fireball.getDamage());
                            fireball_list.RemoveAt(sub);
                            sub--;
                            score += 1;
                        }
                    }
                    //knife damage/kill zombie
                    for (int sub = 0; sub < knife_list.Count; sub++)
                    {
                        PlayerProjectile current_knife = knife_list[sub];
                        if (current_knife.getBoundingBox().Intersects(current_zombie.getBoundingBox()))
                        {
                            if (!zombie_hit_sound.Play())
                            {
                                zombie_hit_sound.Play();
                            }
                            current_zombie.isHit(current_knife.getDamage());
                            knife_list.RemoveAt(sub);
                            sub--;
                            score += 1;
                        }
                    }
                    //zombie death logic
                    if (current_zombie.isDead())
                    {
                        zombie_list.RemoveAt(subscript);
                        subscript--;
                        score += 3;
                        generic_zombie_kills++;
                    }
                    //zombie logic
                }

                //health item logic
                HamburgerTimedSpawn(gameTime);
                foreach (HealthItem current in hamburger_list)
                {
                    current.Update(gameTime, Window.ClientBounds);
                }
                for (int subscript = 0; subscript < hamburger_list.Count; subscript++)
                {
                    HealthItem current_hi = hamburger_list[subscript];
                    if (current_hi.getBoundingBox().Intersects(chrono.getBoundingBox()))
                    {
                        if (!item_health_sound.Play())
                        {
                            item_health_sound.Play();   
                        }
                        chrono.addHealth(current_hi.getHealthBoost());
                        hamburger_list.RemoveAt(subscript);
                        subscript--;
                    }

                }
                //health and stamina bar Update
                staminabar.Update(gameTime, Window.ClientBounds);
                healthbar.Update(gameTime, Window.ClientBounds);
                //records how long game has been played
                minute_time = gameTime.TotalGameTime.TotalMinutes;
                seconds_time = gameTime.TotalGameTime.TotalSeconds - (int)minute_time*60;

                //knife items spawn
                KnifeTimedSpawn(gameTime);
                foreach (HealthItem current in knife_ammo_list)
                {
                    current.Update(gameTime, Window.ClientBounds);
                }
                for (int subscript = 0; subscript < knife_ammo_list.Count; subscript++)
                {
                    HealthItem current_k = knife_ammo_list[subscript];
                    if (current_k.getBoundingBox().Intersects(chrono.getBoundingBox()))
                    {
                        if (!item_knife_sound.Play())
                        {
                            item_knife_sound.Play();   
                        }
                        chrono.addKnifeAmmo(current_k.getKnifeBoost());
                        knife_ammo_list.RemoveAt(subscript);
                        subscript--;
                    }

                }
                //fireball items spawn
                FireballTimedSpawn(gameTime);
                foreach (HealthItem current in fireball_ammo_list)
                {
                    current.Update(gameTime, Window.ClientBounds);
                }
                for (int subscript = 0; subscript < fireball_ammo_list.Count; subscript++)
                {
                    HealthItem current_f = fireball_ammo_list[subscript];
                    if (current_f.getBoundingBox().Intersects(chrono.getBoundingBox()))
                    {
                        if (!item_fireball_sound.Play())
                        {
                            item_fireball_sound.Play();
                        }
                        chrono.addFireballAmmo(current_f.getFireballBoost());
                        fireball_ammo_list.RemoveAt(subscript);
                        subscript--;
                    }
                }

                //Boss Logic
                //  Boss Spawn
                if (generic_zombie_kills > 12 && !boss1_spawned)
                {
                    if (!boss_spawn_sound.Play())
                        {
                            boss_spawn_sound.Play();   
                        }
                    boss1_spawned = true;
                    boss = new Boss1(boss_texture,
                                            new Vector2(Window.ClientBounds.Width / 2, Window.ClientBounds.Height + 100),
                                            new Point(96, 96), Point.Zero, Point.Zero, new Point(4, 4), 
                                            new Vector2(1, 1), chrono_ms_per_frame, chrono, 1000, 50);
                    boss_healthbar = new HealthBar(boss_healthbar_texture, new Vector2(0, 100), new Point(200, 36), Point.Zero,
                                Point.Zero, new Point(1, 1), Vector2.Zero, 0, boss);
                    zombie_next_spawn = 2000;
                    MediaPlayer.Stop();
                    MediaPlayer.Play(boss_music);
                }
                //following logic can only take place after boss has spawned (been instantiated)
                if (boss1_spawned)
                {
                    //spawn slime (appears as if boss is projecting them)
                    slime_spawn_timer += gameTime.ElapsedGameTime.Milliseconds;
                    if (slime_spawn_timer > slime_next_spawn)
                    {
                        if (!slime_sound.Play())
                        {
                            slime_sound.Play();
                        }
                        slime = new ChasingSlime(slime_texture, boss.getPosition(), new Point(73, 28), Point.Zero,
                                                    Point.Zero, new Point(6, 2), new Vector2(4, 4), chrono_ms_per_frame,
                                                    chrono, 100, 100);
                        slime_list.Add(slime);
                        slime_spawn_timer = 0;
                    }
                    //boss update
                    boss.Update(gameTime, Window.ClientBounds);
                    //boss health bar update
                    boss_healthbar.Update(gameTime, Window.ClientBounds);
                    this.SlimeCollision();
                    //game is won when boss dies
                    if (boss.isDead())
                    {
                        won_sound.Play();
                        MediaPlayer.Stop();
                        MediaPlayer.Play(won_game_music);
                        won_game = true;
                    }

                    //fireball damage boss
                    for (int sub = 0; sub < fireball_list.Count; sub++)
                    {
                        PlayerProjectile current_fireball = fireball_list[sub];
                        if (current_fireball.getBoundingBox().Intersects(boss.getBoundingBox()))
                        {
                            if (!zombie_hit_sound.Play())
                            {
                                zombie_hit_sound.Play();
                            }
                            boss.isHit(current_fireball.getDamage());
                            fireball_list.RemoveAt(sub);
                            sub--;
                        }
                    }
                    //knife damage boss
                    for (int sub = 0; sub < knife_list.Count; sub++)
                    {
                        PlayerProjectile current_knife = knife_list[sub];
                        if (current_knife.getBoundingBox().Intersects(boss.getBoundingBox()))
                        {
                            if (!zombie_hit_sound.Play())
                            {
                                zombie_hit_sound.Play();
                            }
                            boss.isHit(current_knife.getDamage());
                            knife_list.RemoveAt(sub);
                            sub--;
                        }
                    }
                    //boss damage player
                    if (boss.getBoundingBox().Intersects(chrono.getBoundingBox()))
                    {
                        if (!chrono_hit_sound.Play())
                        {
                            chrono_hit_sound.Play();   
                        }
                        chrono.isHit(100);
                    }
                }
                //slime update
                foreach (ChasingSlime current in slime_list)
                {
                    current.Update(gameTime, Window.ClientBounds);
                }

                //end of game boolean
                if (chrono.isDead())
                {
                    lose_sound.Play();
                    MediaPlayer.Stop();
                    MediaPlayer.Play(lose_game_music);
                    chrono.setHealth(0);
                    healthbar.setHealth(0);
                    lost_game = true;
                }

                //end of gameplay Update
            }
            else if (lost_game)
            {
                //TODO: dead update methods
                //reset game if spacebar is pressed
                if (old_ks.IsKeyUp(Keys.Space) && ks.IsKeyDown(Keys.Space) || gs.Buttons.X== ButtonState.Pressed && old_gs.Buttons.X == ButtonState.Released)
                {
                    this.ResetGame();
                }
            }

            
            old_ks = ks;
            old_gs= gs;

            base.Update(gameTime);
        }

        /// <summary>
        /// This is called when the game should draw itself.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        /// 

        protected override void Draw(GameTime gameTime)
        {
            GraphicsDevice.Clear(Color.CornflowerBlue);

            // TODO: Add your drawing code here
            KeyboardState ks = Keyboard.GetState();
            spriteBatch.Begin();
            //if intro is playing
            if (is_intro)
            {
                spriteBatch.Draw(intro_background, game_window, Color.White);
                spriteBatch.Draw(intro_press_start,
                                    new Vector2((2* Window.ClientBounds.Width) / 5 - intro_press_start.Width / 3,
                                        Window.ClientBounds.Height / 3 - intro_press_start.Height / 3),
                                    Color.White);
                spriteBatch.Draw(howtobutton,
                    new Vector2(Window.ClientBounds.Width / 2 - howtobutton.Width / 2,
                        Window.ClientBounds.Height / 2 - howtobutton.Height / 2),
                    Color.White);
                spriteBatch.DrawString(stat_font, ">Press Start Button to Play\n>Press Y Button for Instructions", new Vector2(Window.ClientBounds.Width / 3, (3* Window.ClientBounds.Height) / 5), Color.Black);
                if (showing_instructions)
                {
                    spriteBatch.Draw(howtoplay, Vector2.Zero, Color.White);
                }
            }
            //if game was won
            else if (won_game)
            {
                //TODO: Update WIN screen
                spriteBatch.Draw(won_bg_texture, game_window, Color.White);  //added from Andrea
                spriteBatch.DrawString(stat_font, "Score: " + score,
                                        new Vector2((9 * Window.ClientBounds.Width) / 20, (Window.ClientBounds.Height / 3) + 20),
                                        Color.White);
                spriteBatch.DrawString(stat_font, "Time Survived: \n" + (int)minute_time + " minutes and " + (int)seconds_time + " seconds",
                                        new Vector2((9 * Window.ClientBounds.Width) / 20, Window.ClientBounds.Height / 2),
                                        Color.White);
                spriteBatch.DrawString(stat_font, "Zombies Killed: " + generic_zombie_kills,
                                        new Vector2((9 * Window.ClientBounds.Width) / 20, (Window.ClientBounds.Height * 2) / 3),
                                        Color.White);
                //draws reset instruction
                String reset_string = "PRESS SPACEBAR OR X TO PLAY AGAIN";
                spriteBatch.DrawString(stat_font, reset_string,
                                        new Vector2((Window.ClientBounds.Width / 2) - (stat_font.MeasureString(reset_string).X/2),
                                                    (4 * Window.ClientBounds.Height) / 5), Color.White);
            }
            //if player's not dead (normal gameplay)
            else 
            {
                //background
                spriteBatch.Draw(classroom_background, game_window, background_color);

                //player1 draw
                chrono.Draw(gameTime, spriteBatch);
                //projectile(fireball) draw
                foreach (Sprite current in fireball_list)
                {
                    current.Draw(gameTime, spriteBatch);
                }
                //knife draw
                foreach (Sprite current in knife_list)
                {
                    current.Draw(gameTime, spriteBatch);
                }
                //font text Draw
                if (ks.IsKeyDown(Keys.L) || old_gs.Buttons.A == ButtonState.Pressed)
                {
                    //only draw text if player has ammo
                    if (chrono.getFireballAmmo() > 0)
                    {
                        Vector2 shout_measurement = projectile_font.MeasureString(fireball_shout1);
                        spriteBatch.DrawString(stat_font, fireball_shout1,
                                                new Vector2(Window.ClientBounds.Width / 2 - shout_measurement.X / 2,
                                                    Window.ClientBounds.Height / 6 - shout_measurement.Y / 2),
                                                fireball_font_color);
                    }
                }
                if (ks.IsKeyDown(Keys.K) || old_gs.Buttons.B == ButtonState.Pressed)
                {
                    //only draw text if player has ammo
                    if (chrono.getKnifeAmmo() > 0)
                    {
                        Vector2 shout_measurement = projectile_font.MeasureString(knife_shout1);
                        spriteBatch.DrawString(stat_font, knife_shout1,
                                                new Vector2(Window.ClientBounds.Width / 2 - shout_measurement.X / 2,
                                                    Window.ClientBounds.Height / 6 - shout_measurement.Y / 2),
                                                knife_font_color);
                    }
                }
                //zombie draw
                foreach (ChasingEnemy current in zombie_list)
                {
                    current.Draw(gameTime, spriteBatch);
                }
                //health item draw
                foreach (HealthItem current in hamburger_list)
                {
                    current.Draw(gameTime, spriteBatch);
                }
                // knife ammo draw
                foreach (HealthItem current in knife_ammo_list)
                {
                    current.Draw(gameTime, spriteBatch);
                }
                //Fireball Ammo Draw
                foreach (HealthItem current in fireball_ammo_list)
                {
                    current.Draw(gameTime, spriteBatch);
                }
                //HUD bars (stamina, health, andscore)
                staminabar.Draw(gameTime, spriteBatch);
                healthbar.Draw(gameTime, spriteBatch);
                int int_health = chrono.getHealth();
                String string_health = int_health.ToString();
                spriteBatch.DrawString(projectile_font, string_health, new Vector2(80, 0), Color.White);
                String string_score = score.ToString();
                spriteBatch.DrawString(projectile_font, "Score: " + string_score, new Vector2((Window.ClientBounds.Width / 2) - 50, 0), Color.Gold);
                //HUD bars (sword/knife ammo)
                string string_sword_ammo = chrono.getKnifeAmmo().ToString();
                spriteBatch.DrawString(projectile_font, string_sword_ammo, new Vector2(Window.ClientBounds.Width - 100, 0), Color.Silver);
                spriteBatch.Draw(sword_texture, new Vector2(Window.ClientBounds.Width - 150, 0), Color.White);
                //HUD bars (fireball ammo)
                string string_fire_ammo = chrono.getFireballAmmo().ToString();
                spriteBatch.DrawString(projectile_font, string_fire_ammo, new Vector2(Window.ClientBounds.Width - 200, 0), Color.Orange);
                spriteBatch.Draw(fireball_texture, new Vector2(Window.ClientBounds.Width - 250, 0), Color.White);
                //boss draw
                if (boss1_spawned)
                {
                    boss.Draw(gameTime, spriteBatch);
                    //TODO: boss health bar
                    boss_healthbar.Draw(gameTime, spriteBatch);

                }
                //slime draw
                foreach (ChasingSlime current in slime_list)
                {
                    current.Draw(gameTime, spriteBatch);
                }

                //background when you lose
                if (lost_game)
                {
                    spriteBatch.Draw(lost_bg_texture, game_window, Color.White);
                    spriteBatch.DrawString(stat_font, "Score: " + score,
                                            new Vector2((9 * Window.ClientBounds.Width) / 20, (Window.ClientBounds.Height / 3) + 20),
                                            Color.Yellow);
                    spriteBatch.DrawString(stat_font, "Time Survived: \n" + (int)minute_time + " minutes and " + (int)seconds_time + " seconds",
                                            new Vector2((9 * Window.ClientBounds.Width) / 20, Window.ClientBounds.Height / 2),
                                            Color.Yellow);
                    spriteBatch.DrawString(stat_font, "Zombies Killed: " + generic_zombie_kills,
                                            new Vector2((9 * Window.ClientBounds.Width) / 20, (Window.ClientBounds.Height *2) / 3),
                                            Color.Yellow);
                    //draws reset instruction
                    String reset_string = "PRESS SPACEBAR OR X TO PLAY AGAIN";
                    spriteBatch.DrawString(stat_font, reset_string, 
                                            new Vector2((Window.ClientBounds.Width/2) - (stat_font.MeasureString(reset_string).X/2), 
                                                        (4 * Window.ClientBounds.Height) / 5), Color.White);
                }
                //TODO: testing running stamina
                /*String players_stamina = chrono.getStamina().ToString();
                spriteBatch.DrawString(stat_font, "Stamina: " + players_stamina, new Vector2(Window.ClientBounds.Width / 2, 500), Color.White);
                */
                //end of gamplay draw
            }

            spriteBatch.End();

            base.Draw(gameTime);
        }
    }
}
