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
    class StaminaBar : Sprite
    {
        protected Player1 player;
        protected int stamina;

        public StaminaBar(Texture2D textureImage, Vector2 position, Point frameSize,
                        Point collisionOffset, Point currentFrame, Point sheetSize, Vector2 speed,
                        int millisecondsPerFrame, Player1 player)            
            : base(textureImage, position, frameSize, collisionOffset, currentFrame, sheetSize, speed, millisecondsPerFrame)
        {
            this.player = player;
        }

        public override void Update(GameTime gameTime, Rectangle clientBounds)
        {
            base.Update(gameTime, clientBounds);

            stamina = player.getStamina();
            if (stamina > 210)
                currentFrame.Y = 0;
            else if (stamina > 180)
                currentFrame.Y = 1;
            else if (stamina > 180)
                currentFrame.Y = 2;
            else if (stamina > 120)
                currentFrame.Y = 3;
            else if (stamina > 90)
                currentFrame.Y = 4;
            else if (stamina > 60)
                currentFrame.Y = 5;
            else if (stamina > 30)
                currentFrame.Y = 6;
            else if (stamina > 0)
                currentFrame.Y = 7;
            else
                currentFrame.Y = 8;
        }

        public override void Draw(GameTime gameTime, SpriteBatch spriteBatch)
        {
            base.Draw(gameTime, spriteBatch);
        }
    }
}
