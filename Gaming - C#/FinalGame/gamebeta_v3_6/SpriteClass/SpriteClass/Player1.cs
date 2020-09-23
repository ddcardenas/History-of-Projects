using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Audio;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.GamerServices;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using Microsoft.Xna.Framework.Media;

namespace SpriteClass
{
    class Player1 : Sprite
    {
        //direction booleans
        protected Boolean going_right = false;
        protected Boolean going_left = false;
        protected Boolean going_up = false;
        protected Boolean going_down = false;
        protected Boolean standing_still = true;

        protected Vector2 default_speed;
        protected KeyboardState old_ks = Keyboard.GetState();
        //protected Vector2 collisionOffset = new Vector2(55, 45);
        protected int knife_ammo = 8;
        protected int fire_ammo = 16;
        protected int player_health = 100;
        protected int player_max_health = 100;
        protected Boolean being_hit = false;
        protected int default_millisecondsPerFrame;

        //running stamina testing
        protected int stamina_timer = 0;
        protected int stamina_next_increase = 0;
        protected Boolean can_run = true;
        protected int stamina = 240;

        public Player1(Texture2D textureImage, Vector2 position, Point frameSize,
                        Point collisionOffset, Point currentFrame, Point sheetSize, Vector2 speed,
                        int millisecondsPerFrame)            
            : base(textureImage, position, frameSize, collisionOffset, currentFrame, sheetSize, speed, millisecondsPerFrame)
        {
            default_speed = speed;
            default_millisecondsPerFrame = millisecondsPerFrame;
        }


        public override void Update(GameTime gameTime, Rectangle clientBounds)
        {
            base.Update(gameTime, clientBounds);
            //Update animation frame
            timeSinceLastFrame += gameTime.ElapsedGameTime.Milliseconds;
            if (timeSinceLastFrame > millisecondsPerFrame)
            {
                timeSinceLastFrame = 0;
                ++currentFrame.X;
                if (currentFrame.X >= sheetSize.X)
                {
                    currentFrame.X = 0;
                }
            }

            //TODO: code goes here     

            Vector2 inputDirection = Vector2.Zero;
            KeyboardState ks = Keyboard.GetState();
            GamePadState gs = GamePad.GetState(PlayerIndex.One);

            //only move if not lost game
            if (!Game1.lost_game)
            {
                //controller logic

                
                if (gs.IsConnected)
                {
                    //TODO: add gamepad logic
                }
                        //turn the motor: left low frequency, right high frequency
                        //GamePad.SetVibration(PlayerIndex.One, 1.0f, 0.0f);
                   



                    //movement logic
                    if (ks.IsKeyDown(Keys.W) || gs.DPad.Up == ButtonState.Pressed)
                    {
                        going_up = true;
                        standing_still = false;
                        inputDirection.Y -= 1;
                        currentFrame.Y = 0;
                    }
                    else
                    {
                        going_up = false;
                    }

                    if (ks.IsKeyDown(Keys.S) || gs.DPad.Down == ButtonState.Pressed)
                    {
                        going_down = true;
                        standing_still = false;
                        inputDirection.Y += 1;
                        currentFrame.Y = 1;
                    }
                    else
                    {
                        going_down = false;
                    }

                    if (ks.IsKeyDown(Keys.A) || gs.DPad.Left == ButtonState.Pressed)
                    {
                        going_left = true;
                        standing_still = false;
                        inputDirection.X -= 1;
                        if (!going_up || !going_down)
                        {
                            currentFrame.Y = 2;
                        }
                    }
                    else
                    {
                        going_left = false;
                    }

                    if (ks.IsKeyDown(Keys.D) || gs.DPad.Right == ButtonState.Pressed)
                    {
                        going_right = true;
                        standing_still = false;
                        inputDirection.X += 1;
                        if (!going_up || !going_down)
                        {
                            currentFrame.Y = 3;
                        }
                    }
                    else
                    {
                        going_right = false;
                    }
                }
                //standing still animation
                if (!going_up && !going_down && !going_left && !going_right)
                {
                    standing_still = true;
                    currentFrame.Y = 4;
                }
                //running logic
                /*if (old_ks.IsKeyUp(Keys.J) && ks.IsKeyDown(Keys.J))
                {
                    speed *= 2;
                }
                if (ks.IsKeyUp(Keys.J))
                {
                    speed = default_speed;
                }*/
                stamina_timer += gameTime.ElapsedGameTime.Milliseconds;
                if (ks.IsKeyDown(Keys.J) || gs.Triggers.Left >0)
                {
                    if (can_run)
                    {
                        speed = default_speed * 2;
                        millisecondsPerFrame = default_millisecondsPerFrame / 2;
                        stamina -= 2;
                    }
                    stamina_timer = 0;
                }
                else if (stamina_timer >= stamina_next_increase)
                {
                    can_run = true;
                    if (stamina < 240)
                    {
                        stamina += 1;
                    }
                    stamina_next_increase = 0;
                    speed = default_speed;
                    millisecondsPerFrame = default_millisecondsPerFrame;
                }
                if (stamina <= 0)
                {
                    stamina = 0;
                    can_run = false;
                    stamina_next_increase = 2000;
                    speed = default_speed;
                    millisecondsPerFrame = default_millisecondsPerFrame;
                }

                //being hit animation
                if (being_hit)
                {
        
                    currentFrame.Y = 3;
                }

                position += inputDirection * speed;  //changes both X and Y components of position

                //game window boundaries
                if (position.X + collisionOffset.X - 15 < 0)
                    position.X = 0 - collisionOffset.X + 15;
                if (position.X > clientBounds.Width - frameSize.X + collisionOffset.X - 15)
                    position.X = clientBounds.Width - frameSize.X + collisionOffset.X - 15;
                if (position.Y - 120 < 0)
                    position.Y = 120;
                if (position.Y > clientBounds.Height - frameSize.Y + collisionOffset.Y - 25)
                    position.Y = clientBounds.Height - frameSize.Y + collisionOffset.Y - 25;

                //lose game music
                if (this.isDead())
                {
                    MediaPlayer.Stop();
                    MediaPlayer.Play(Game1.lose_game_music);
                }

                //health at lose
                if (Game1.lost_game)
                {
                    player_health = 0;
                }
                if (being_hit)
                {
                    GamePad.SetVibration(PlayerIndex.One, 1.0f, 5.0f);
                }
                else
                {
                    GamePad.SetVibration(PlayerIndex.One, 0f, 0f);
                }

                old_ks = ks;
            }
        
  

        public override void Draw(GameTime gameTime, SpriteBatch spriteBatch)
        {            
            base.Draw(gameTime, spriteBatch);


        }

        //these are get methods 
        public int positionX()
        {
            return (int)this.position.X;
        }
        public int positionY()
        {
            return (int)this.position.Y;
        }
        public Vector2 getPosition()
        {
            return this.position;
        }
        public Vector2 getDirection()
        {
            Vector2 direction = new Vector2(0, 0);
            if (going_up)
                direction.Y -= 1;
            else if (going_down)
                direction.Y += 1;
            if (going_left)
                direction.X -= 1;
            else if (going_right)
                direction.X += 1;
            if (standing_still)
                direction.Y += 1;
            return direction;

        }
        public Boolean isGoingUp()
        {
            return going_up;
        }
        public Boolean isGoingDown()
        {
            return going_down;
        }
        public Boolean isGoingLeft()
        {
            return going_left;
        }
        public Boolean isGoingRight()
        {
            return going_right;
        }
        public Boolean isStandingStill()
        {
            return standing_still;
        }
        public Point getOffset()
        {
            return this.collisionOffset;
        }
        public virtual void isHit(int damage)
        {
            player_health -= damage;
        }
        public virtual Boolean isHit()
        {
            return being_hit;
        }
        public virtual void setBeingHit(Boolean beingHit)
        {
            this.being_hit = beingHit;
        }
        public virtual Boolean isDead()
        {
            if (player_health <= 0)
            {
                return true;
            }
            else
                return false;
        }
        public virtual void addHealth(int health_boost)
        {
            if (health_boost + player_health < player_max_health)
            {
                player_health += health_boost;
            }
            else
            {
                player_health = player_max_health;
            }
        }
        public virtual float getHealthPercentage()
        {
            return ((float)player_health / (float)player_max_health);
        }
        public virtual int getHealth()
        {
            return player_health;
        }
        public virtual int getKnifeAmmo()
        {
            return knife_ammo;
        }
        public virtual void addKnifeAmmo(int knife_item)
        {
            this.knife_ammo += knife_item;
        }
        public virtual int getFireballAmmo()
        {
            return fire_ammo;
        }
        public virtual void addFireballAmmo(int fire_item)
        {
            this.fire_ammo += fire_item;
        }
        public virtual void setHealth(int health)
        {
            player_health = health;
        }
        public virtual int getStamina()
        {
            return stamina;
        }

    }
}
