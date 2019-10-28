class Particle
{
  PVector pos;
  PVector prevPos;
  PVector vel;
  PVector acc;
  int maxSpeed;
  int colour;
  Particle()
 {
   colour=0;
   pos = new PVector(random(width),random(height));
   prevPos = pos.copy();
   vel = new PVector(0,0);
   acc = new PVector(0,0);
   maxSpeed = 4;
 }
  void update()
  {
   vel.add(acc);
   vel.limit(maxSpeed); 
   pos.add(vel);
   acc.mult(0);
  }
  
  void applyForce(PVector force)
  {
   acc.add(force); 
  }
  
  void display()
  {
   //stroke(floor(random(0,255)),floor(random(0,255)),floor(random(0,255)),5);
   stroke(colour, 255,255, 5);
   colour = colour+1;
   if(colour>255)
   colour=0;
   strokeWeight(1);
   line(pos.x, pos.y, prevPos.x, prevPos.y);
   updatePrevPos();
  }
  
  void updatePrevPos()
  {
    prevPos.x = pos.x;
    prevPos.y = pos.y;
  }
  
  void edges()
  {
   if(pos.x>width)
   {
   pos.x = 0;
   updatePrevPos();
   }
   if(pos.x<0)
   {
   pos.x = width;
   updatePrevPos();
   }
   if(pos.y>height)
   {
   pos.y = 0;
   updatePrevPos();
   }
   if(pos.y<0)
   {
   pos.y = height;
   updatePrevPos();
   }
  }
  
  void follow(PVector vectors[])
  {
    int x =floor(pos.x/scl);
    int y= floor(pos.y/scl);
    int idx = x + y*cols;
    idx = idx%400;
   // print(idx+" ");
    PVector force = vectors[idx];
    this.applyForce(force);
  }
}
