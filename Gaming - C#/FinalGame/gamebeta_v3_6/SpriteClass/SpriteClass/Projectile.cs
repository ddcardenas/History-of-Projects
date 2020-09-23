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

//Example 15: Projectiles
namespace SpriteClass
{    
    public class Projectile : Sprite
    {
        protected Boolean out_of_bounds = false;
        protected Rectangle game_window;

        public Projectile(Texture2D textureImage, Vector2 position, Point frameSize,
                        Point collisionOffset, Point currentFrame, Point sheetSize, Vector2 speed,
                        int millisecondsPerFrame)            
            : base(textureImage, position, frameSize, collisionOffset, currentFrame, sheetSize, speed, millisecondsPerFrame)
        {
            
        }

        public Boolean isOutOfBounds()
        {
            return out_of_bounds;
        }

        public override void Update(GameTime gameTime, Rectangle clientBounds)
        {            
            base.Update(gameTime, clientBounds);

            //TODO: code goes here     
			position += speed;  //changes both X and Y components of position  
     
            
            if (position.X >= clientBounds.Width)
            {
                out_of_bounds = true;
            }
            //if the sprite leaves the game window, then out_of_bounds is true
            game_window = new Rectangle(0, 0, clientBounds.Width, clientBounds.Height);
            if (!game_window.Contains(this.collisionBox))
            {
                out_of_bounds = true;
            }

        }

        public override void Draw(GameTime gameTime, SpriteBatch spriteBatch)
        {            
            base.Draw(gameTime, spriteBatch);
        }
    }
}
