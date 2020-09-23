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
    class Boss1 : ChasingEnemy
    {
        protected Boolean spawn_slime = false;
        protected int slime_spawn_timer = 0;
        protected int slime_next_spawn = 5000;
        //protected Player1 player;
        //int boss_health;
        protected int max_boss_health;

        public Boss1(Texture2D textureImage, Vector2 position, Point frameSize,
                        Point collisionOffset, Point currentFrame, Point sheetSize, Vector2 speed,
                        int millisecondsPerFrame, Player1 player, int enemyHealth, int damage)
            : base(textureImage, position, frameSize, collisionOffset, currentFrame, sheetSize, 
                    speed, millisecondsPerFrame, player, enemyHealth, damage)
        {
            //this.player = player;
            this.max_boss_health = enemyHealth;
        }


        public virtual Boolean SpawnSlime()
        {

            return spawn_slime;
        }
        public virtual Vector2 getPosition()
        {
            return position;
        }

        public override void Update(GameTime gameTime, Rectangle clientBounds)
        {
            base.Update(gameTime, clientBounds);
            //spawn slime projectiles
            slime_spawn_timer += gameTime.ElapsedGameTime.Milliseconds;
            if (slime_spawn_timer > slime_next_spawn)
            {
                spawn_slime = true;
                slime_spawn_timer = 0;
            }
            else
                spawn_slime = false;

        }
        public override void Draw(GameTime gameTime, SpriteBatch spriteBatch)
        {
            base.Draw(gameTime, spriteBatch);

        }
    }
}
