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
    public class Sprite
    {
        //basics
        protected Texture2D textureImage;
        protected Vector2 position;
        protected Color tint = Color.White;
        protected Vector2 speed;

        //animation
        protected Point frameSize;        
        protected Point currentFrame;
        protected Point sheetSize;

        //animation timing
        protected int timeSinceLastFrame = 0;
        protected int millisecondsPerFrame;
        const int defaultMillisecondsPerFrame = 16;

        //bounding box offset
        protected Point collisionOffset;
        //collision rectangle
        protected Rectangle collisionBox;
                     
        public Sprite(Texture2D textureImage, Vector2 position, Point frameSize,
                        Point collisionOffset, Point currentFrame, Point sheetSize, Vector2 speed,
                        int millisecondsPerFrame)
        {
            this.textureImage = textureImage;
            this.position = position;
            this.frameSize = frameSize;
            this.collisionOffset = collisionOffset;
            this.currentFrame = currentFrame;
            this.sheetSize = sheetSize;
            this.speed = speed;
            this.millisecondsPerFrame = millisecondsPerFrame;
        }
        //this method creates a collision rectange for the sprite
        /*
        public virtual void CollisionRect()
        {
            collisionBox = new Rectangle((int)position.X, (int)position.Y, frameSize.X, frameSize.Y);
            collisionBox.Inflate(collisionOffset, collisionOffset);

        }*/

        public virtual Rectangle getBoundingBox()
        {
            collisionBox = new Rectangle((int)position.X + collisionOffset.X, (int)position.Y + collisionOffset.Y, 
                                            frameSize.X - collisionOffset.X*2, frameSize.Y - collisionOffset.Y*2);
            //collisionBox.Inflate(collisionOffset, collisionOffset);
            return collisionBox;
        }

        public virtual void Update(GameTime gameTime, Rectangle clientBounds)
        {
            //Update animation frame
            timeSinceLastFrame += gameTime.ElapsedGameTime.Milliseconds;
            if (timeSinceLastFrame > millisecondsPerFrame)
            {
                timeSinceLastFrame = 0;
                ++currentFrame.X;
                if (currentFrame.X >= sheetSize.X)
                {
                    currentFrame.X = 0;
                    ++currentFrame.Y;
                    if (currentFrame.Y >= sheetSize.Y)
                        currentFrame.Y = 0;
                }
            }
        }

        public virtual void Draw(GameTime gameTime, SpriteBatch spriteBatch)
        {
            //Draw the sprite
            spriteBatch.Draw(textureImage,
                position,
                new Rectangle(currentFrame.X * frameSize.X,
                    currentFrame.Y * frameSize.Y,
                    frameSize.X, frameSize.Y),
                tint, 0, Vector2.Zero,
                1f, SpriteEffects.None, 0);
        }
    } //end of Sprite
}
