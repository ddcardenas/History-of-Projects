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
    class PlayerProjectile : Projectile
    {
        protected Player1 player;
        protected Vector2 direction;
        protected Vector2 realposition;
        protected Boolean going_up;
        protected Boolean going_down;
        protected Boolean going_left;
        protected Boolean going_right;
        protected int damage;

        public PlayerProjectile(Texture2D textureImage, Vector2 position, Point frameSize,
                        Point collisionOffset, Point currentFrame, Point sheetSize, Vector2 speed,
                        int millisecondsPerFrame)            
            : base(textureImage, position, frameSize, collisionOffset, currentFrame, sheetSize, speed, millisecondsPerFrame)
        {
            
        }
        public PlayerProjectile(Texture2D textureImage, Vector2 position, Point frameSize,
                        Point collisionOffset, Point currentFrame, Point sheetSize, Vector2 speed,
                        int millisecondsPerFrame, Player1 player, int damage)
            : base(textureImage, position, frameSize, collisionOffset, currentFrame, sheetSize, speed, millisecondsPerFrame)
        {
            this.player = player;
            this.damage = damage;
            direction = player.getDirection();
            realposition = position;
            going_up = player.isGoingUp();
            going_down = player.isGoingDown();
            going_left = player.isGoingLeft();
            going_right = player.isGoingRight();
        }

        public virtual int getDamage()
        {
            return damage;
        }

        public override void Update(GameTime gameTime, Rectangle clientBounds)
        {            
            base.Update(gameTime, clientBounds);

            //TODO: code goes here 
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
            currentFrame.Y = 6;
            if (going_left)
                currentFrame.Y = 0;
            if (going_up)
                currentFrame.Y = 2;
            if (going_right)
                currentFrame.Y = 4;
            if (going_left && going_up)
                currentFrame.Y = 1;
            if (going_up && going_right)
                currentFrame.Y = 3;
            if (going_right && going_down)
                currentFrame.Y = 5;
            if (going_down && going_left)
                currentFrame.Y = 7;
                
			realposition += direction * speed;  //changes both X and Y components of position  
            position = realposition;
            position.X += player.getOffset().X;
            position.Y += player.getOffset().Y;
        }

        public override void Draw(GameTime gameTime, SpriteBatch spriteBatch)
        {            
            base.Draw(gameTime, spriteBatch);
        }








    }
}
