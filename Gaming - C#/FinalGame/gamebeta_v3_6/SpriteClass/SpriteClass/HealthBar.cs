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
    class HealthBar : Sprite
    {
        protected Boolean is_player = false;
        protected Boolean is_boss = false;
        protected int max_healthbar_width;
        protected Player1 player;
        protected Boss1 boss;

        public HealthBar(Texture2D textureImage, Vector2 position, Point frameSize,
                        Point collisionOffset, Point currentFrame, Point sheetSize, Vector2 speed,
                        int millisecondsPerFrame, Player1 player)            
            : base(textureImage, position, frameSize, collisionOffset, currentFrame, sheetSize, speed, millisecondsPerFrame)
        {
            max_healthbar_width = frameSize.X;
            this.player = player;
            is_player = true;
        }
        public HealthBar(Texture2D textureImage, Vector2 position, Point frameSize,
                Point collisionOffset, Point currentFrame, Point sheetSize, Vector2 speed,
                int millisecondsPerFrame, Boss1 boss)
            : base(textureImage, position, frameSize, collisionOffset, currentFrame, sheetSize, speed, millisecondsPerFrame)
        {
            max_healthbar_width = frameSize.X;
            this.boss = boss;
            is_boss = true;
        }

        public virtual void setHealth(float health_percentage)
        {
            float healthbar_width = health_percentage * max_healthbar_width;
            frameSize.X = (int)healthbar_width;
        }

        public override void Update(GameTime gameTime, Rectangle clientBounds)
        {            
            base.Update(gameTime, clientBounds);

            //TODO: code goes here   
            if (is_player)
            {
                float health_percentage = player.getHealthPercentage();
                float healthbar_width = health_percentage * max_healthbar_width;
                frameSize.X = (int)healthbar_width;
            }
            else if (is_boss)
            {
                float health_percentage = boss.getHealthPercentage();
                float healthbar_width = health_percentage * max_healthbar_width;
                frameSize.X = (int)healthbar_width;
            }
        }

        public override void Draw(GameTime gameTime, SpriteBatch spriteBatch)
        {            
            base.Draw(gameTime, spriteBatch);
        }
    }
}
