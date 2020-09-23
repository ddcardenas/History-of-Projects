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

namespace SpriteClass
{
    class ChasingSlime : Sprite
    {
        protected Player1 player;
        protected Vector2 player_position;
        protected int chasing_enemy_health;
        protected int damage;
        protected Boolean first_hit = true;
        protected Rectangle player_bounding_box;

        public ChasingSlime(Texture2D textureImage, Vector2 position, Point frameSize,
                Point collisionOffset, Point currentFrame, Point sheetSize, Vector2 speed,
                int millisecondsPerFrame, Player1 player, int enemyHealth, int damage)
            : base(textureImage, position, frameSize, collisionOffset, currentFrame, sheetSize, speed, millisecondsPerFrame)
        {
            this.player = player;
            this.chasing_enemy_health = enemyHealth;
            this.damage = damage;
        }

        public virtual void isHit(int damage)
        {
            chasing_enemy_health -= damage;
        }
        public virtual Boolean isDead()
        {
            if (chasing_enemy_health <= 0)
                return true;
            else
                return false;
        }
        public virtual int getDamage()
        {
            return damage;
        }
        public virtual void setFirstHit(Boolean firstHit)
        {
            this.first_hit = firstHit;
        }
        public virtual Boolean isFirstHit()
        {
            return first_hit;
        }


        public override void Update(GameTime gameTime, Rectangle clientBounds)
        {
            //base.Update(gameTime, clientBounds);
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
            player_position = player.getPosition();
            player_bounding_box = player.getBoundingBox();

            if (player_bounding_box.X < position.X)
            {
                position.X -= speed.X;
                currentFrame.Y = 0;
            }
            else if (player_bounding_box.X > position.X)
            {
                position.X += speed.X;
                currentFrame.Y = 1;
            }
            if (player_bounding_box.Y < position.Y)
                position.Y -= speed.Y;
            else if (player_bounding_box.Y > position.Y)
                position.Y += speed.Y;

        }

        public override void Draw(GameTime gameTime, SpriteBatch spriteBatch)
        {
            base.Draw(gameTime, spriteBatch);
        }
    }
}

