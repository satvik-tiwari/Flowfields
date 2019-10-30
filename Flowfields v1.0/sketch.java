class Particle
{
  PVector pos;
  PVector prevPos;
  PVector v;
int scl = 20;
int rows, cols;
float zoff = 0;
int num_Particles = 13000;
Particle[] P = new Particle[num_Particles];
float inc = 0.04;
PVector[] flowfield;

void setup()
{
  size(1000,800);
  colorMode(HSB, 255);
   background(0);
  cols = floor(width/scl);
  rows = floor(height/scl);
  for(int i=0; i< P.length; i++)
  {
  P[i] = new Particle();
  }
  flowfield = new PVector[cols*rows];
}

void draw()
{
  float xoff = 0;
  float yoff = 0;
  
  for(int y=0; y<rows; y++)
  {
    xoff= 0;
    for(int x=0; x<cols; x++)                   
    {
      int idx = x + y * cols;
      float angle = noise(xoff, yoff, zoff)*TWO_PI*4;
      v = PVector.fromAngle(angle);
      v.setMag(1);
      flowfield[idx] = v;
      xoff += inc;
      stroke(0);
      
      /*push();
      translate(x*scl, y*scl);
      rotate(v.heading());
      stroke(0,50);
      strokeWeight(2);
      line(0, 0, scl, 0);
      pop();*/
     }
    yoff += inc;
    zoff += 0.00001;
  }
  for(int i=0; i<P.length; i++)
  {
  P[i].follow(flowfield);  
  P[i].update();
  P[i].edges();
  P[i].display();
  }
} 
