import turtle, random, math
'''
I did the turtle.tracer(0, 0) as this prevents the turtle window from 
drawing anything to the screen until each time turtle.update() is called. This stopped the game from lagging

I used turtle.write so the game will show what happend in the spacecraft and not in the terminal

I made a code in the game class that makes sure that the space craft doesn't leave the screen.

When the game ended the spacecraft wouldn't turn anymore which fixed that problem. I did this using an if statement
'''


class Game:
    def __init__(self):
        #Bottom left corner of screen is (0, 0)
        #Top right corner is (500, 500)
        turtle.setworldcoordinates(0, 0, 500, 500)
        cv = turtle.getcanvas()
        cv.adjustScrolls()
        turtle.tracer(0, 0) 
        '''turtle.tracer(0, 0) prevents the turtle window from drawing anything to the screen until each time turtle.update() is called.'''
        #Ensure turtle is running as fast as possible
        turtle.delay(0)
        # self.player = SpaceCraft(start_x, start_y, start_x_velocity, start_y_velocity)
        self.player = SpaceCraft(random.uniform(100, 400), random.uniform(250, 500), random.uniform(-4, 4), random.uniform(-2, 0))
        self.obstacle = Obstacles(random.uniform(0, 500), random.uniform(460, 480), random.uniform(-2, 0), random.uniform(-0.001, 0))
        self.obstacle1 = Obstacles(random.uniform(0, 500), random.uniform(240, 260), random.uniform(-2, 0), random.uniform(-0.001, 0))
        self.obstacle2 = Obstacles(random.uniform(0, 500), random.uniform(0, 20), random.uniform(-2, 0), random.uniform(-0.001, 0))
        self.obstacle3 = Obstacles(random.uniform(0, 500), random.uniform(20, 40), random.uniform(-2, 0), random.uniform(-6, 6))
        self.obstacle4 = Obstacles(random.uniform(0, 500), random.uniform(40, 60), random.uniform(-2, 0), random.uniform(-6, 6))
        self.obstacle5 = Obstacles(random.uniform(0, 500), random.uniform(60, 80), random.uniform(-2, 0), random.uniform(-6, 6))
        self.obstacle6 = Obstacles(random.uniform(0, 500), random.uniform(80, 100), random.uniform(-2, 0), random.uniform(-0.001, 0))
        self.obstacle7 = Obstacles(random.uniform(0, 500), random.uniform(100, 120), random.uniform(0, 2), random.uniform(-6, 6))
        self.obstacle8 = Obstacles(random.uniform(0, 500), random.uniform(120, 140), random.uniform(0, 2), random.uniform(-6, 6))
        self.obstacle9 = Obstacles(random.uniform(0, 500), random.uniform(140, 160), random.uniform(0, 2), random.uniform(-6, 6))
        self.obstacle10 = Obstacles(random.uniform(0, 500), random.uniform(160, 180), random.uniform(0, 2), random.uniform(-0.001, 0))
        self.obstacle11 = Obstacles(random.uniform(0, 500), random.uniform(260, 280), random.uniform(-2, 0), random.uniform(-0.001, 0))
        self.obstacle12 = Obstacles(random.uniform(0, 500), random.uniform(180, 200), random.uniform(-2, 0), random.uniform(-0.001, 0))
        self.obstacle13 = Obstacles(random.uniform(0, 500), random.uniform(200, 220), random.uniform(-2, 0), random.uniform(-6, 6))
        self.obstacle14 = Obstacles(random.uniform(0, 500), random.uniform(220, 240), random.uniform(-2, 0), random.uniform(-6, 6))
        self.obstacle15 = Obstacles(random.uniform(0, 500), random.uniform(280, 300), random.uniform(-2, 0), random.uniform(-6, 6))
        self.obstacle16 = Obstacles(random.uniform(0, 500), random.uniform(300, 320), random.uniform(-2, 0), random.uniform(-0.001, 0))
        self.obstacle17 = Obstacles(random.uniform(0, 500), random.uniform(340, 360), random.uniform(0, 2), random.uniform(-6, 6))
        self.obstacle18 = Obstacles(random.uniform(0, 500), random.uniform(360, 380), random.uniform(0, 2), random.uniform(-6, 6))
        self.obstacle19 = Obstacles(random.uniform(0, 500), random.uniform(380, 400), random.uniform(0, 2), random.uniform(-6, 6))
        self.obstacle20 = Obstacles(random.uniform(0, 500), random.uniform(420, 440), random.uniform(0, 2), random.uniform(-0.001, 0))
        self.gameloop()
        turtle.onkeypress(self.player.thrust, 'Up')
        turtle.onkeypress(self.player.left_turn, 'Left')
        turtle.onkeypress(self.player.right_turn, 'Right')
        #These two lines must always be at the BOTTOM of __init__
        turtle.listen()
        turtle.mainloop()
    def gameloop(self):
        SpaceCraft.move(self.player)
        Obstacles.mover1(self.obstacle)
        Obstacles.mover1(self.obstacle1)
        Obstacles.mover1(self.obstacle2)
        Obstacles.mover1(self.obstacle3)
        Obstacles.mover1(self.obstacle4)
        Obstacles.mover1(self.obstacle5)
        Obstacles.mover1(self.obstacle6)
        Obstacles.mover1(self.obstacle7)
        Obstacles.mover1(self.obstacle8)
        Obstacles.mover1(self.obstacle9)
        Obstacles.mover1(self.obstacle10)
        Obstacles.mover1(self.obstacle11)
        Obstacles.mover1(self.obstacle12)
        Obstacles.mover1(self.obstacle13)
        Obstacles.mover1(self.obstacle14)
        Obstacles.mover1(self.obstacle15)
        Obstacles.mover1(self.obstacle16)
        Obstacles.mover1(self.obstacle17)
        Obstacles.mover1(self.obstacle18)
        Obstacles.mover1(self.obstacle19)
        Obstacles.mover1(self.obstacle20)

        turtle.update() 
        "turtle.update is for the turtle.tracer"
        # turtle.ontimer(self.gameloop, 30)
       
        if self.player.start_y < 20:
            if abs(self.player.x_vel) < 3 and abs(self.player.y_vel) < 3:
                # successful landing
                turtle.write("Successful landing!", font = 25)
                
            else:
                # crashed
                turtle.write("You crashed!", font = 25)
        elif self.player.start_y > 20:
            if self.player.distance(self.obstacle) < 10 or self.player.distance(self.obstacle1) < 10 or self.player.distance(self.obstacle2) < 10 or self.player.distance(self.obstacle3) < 10 or self.player.distance(self.obstacle4) < 10 or self.player.distance(self.obstacle5) < 10 or self.player.distance(self.obstacle6) < 10 or self.player.distance(self.obstacle7) < 10 or self.player.distance(self.obstacle8) < 10 or self.player.distance(self.obstacle9) < 10 or self.player.distance(self.obstacle10) < 10 or self.player.distance(self.obstacle11) < 10 or self.player.distance(self.obstacle12) < 10 or self.player.distance(self.obstacle13) < 10 or self.player.distance(self.obstacle14) < 10 or self.player.distance(self.obstacle15) < 10 or self.player.distance(self.obstacle16) < 10 or self.player.distance(self.obstacle17) < 10 or self.player.distance(self.obstacle18) < 10 or self.player.distance(self.obstacle19) < 10 or self.player.distance(self.obstacle20) < 10:
                turtle.write("You crashed!", font = 25)
                return 
            turtle.ontimer(self.gameloop, 30)
        ''' This code would help the game end and the 
        spacecraft would stop turning in the process'''
        if self.player.start_x > 498:
            self.player.x_vel *= -0.1
            if self.player.start_y > 498:
                self.player.y_vel *= -0.1
        elif self.player.start_x < 2:
            self.player.x_vel *= -0.1
            if self.player.start_y > 498:
                self.player.y_vel *= -0.1
        elif self.player.start_y > 498:
            self.player.y_vel *= -0.1
        ''' This code was made so when the spacecraft was trying to 
        leave the screen it would hit te side like a wall
        '''
        


        if self.obstacle.obj_start_x < 0 or self.obstacle.obj_start_x > 500:
            self.obstacle.obj_velx *= random.uniform(-1.001, -1)
        if self.obstacle1.obj_start_x < 0 or self.obstacle1.obj_start_x > 500:
            self.obstacle1.obj_velx *= random.uniform(-1.001, -1)
        if self.obstacle2.obj_start_x < 0 or self.obstacle2.obj_start_x > 500:
            self.obstacle2.obj_velx *= random.uniform(-1.001, -1)
        if self.obstacle3.obj_start_x < 0 or self.obstacle3.obj_start_x > 500:
            self.obstacle3.obj_velx *= random.uniform(-1.001, -1)
        if self.obstacle4.obj_start_x < 0 or self.obstacle4.obj_start_x > 500:
            self.obstacle4.obj_velx *= random.uniform(-1.001, -1)
        if self.obstacle5.obj_start_x < 0 or self.obstacle5.obj_start_x > 500:
            self.obstacle5.obj_velx *= random.uniform(-1.001, -1)
        if self.obstacle6.obj_start_x < 0 or self.obstacle6.obj_start_x > 500:
            self.obstacle6.obj_velx *= random.uniform(-1.001, -1)
        if self.obstacle7.obj_start_x < 0 or self.obstacle7.obj_start_x > 500:
            self.obstacle7.obj_velx *= random.uniform(-1.001, -1)
        if self.obstacle8.obj_start_x < 0 or self.obstacle8.obj_start_x > 500:
            self.obstacle8.obj_velx *= random.uniform(-1.001, -1)
        if self.obstacle9.obj_start_x < 0 or self.obstacle9.obj_start_x > 500:
            self.obstacle9.obj_velx *= random.uniform(-1.001, -1)
        if self.obstacle10.obj_start_x < 0 or self.obstacle10.obj_start_x > 500:
            self.obstacle10.obj_velx *= random.uniform(-1.001, -1)
        if self.obstacle11.obj_start_x < 0 or self.obstacle11.obj_start_x > 500:
            self.obstacle11.obj_velx *= random.uniform(-1.001, -1)
        if self.obstacle12.obj_start_x < 0 or self.obstacle12.obj_start_x > 500:
            self.obstacle12.obj_velx *= random.uniform(-1.001, -1)
        if self.obstacle13.obj_start_x < 0 or self.obstacle13.obj_start_x > 500:
            self.obstacle13.obj_velx *= random.uniform(-1.001, -1)
        if self.obstacle14.obj_start_x < 0 or self.obstacle14.obj_start_x > 500:
            self.obstacle14.obj_velx *= random.uniform(-1.001, -1)
        if self.obstacle15.obj_start_x < 0 or self.obstacle15.obj_start_x > 500:
            self.obstacle15.obj_velx *= random.uniform(-1.001, -1)
        if self.obstacle16.obj_start_x < 0 or self.obstacle16.obj_start_x > 500:
            self.obstacle16.obj_velx *= random.uniform(-1.001, -1)
        if self.obstacle17.obj_start_x < 0 or self.obstacle17.obj_start_x > 500:
            self.obstacle17.obj_velx *= random.uniform(-1.001, -1)
        if self.obstacle18.obj_start_x < 0 or self.obstacle18.obj_start_x > 500:
            self.obstacle18.obj_velx *= random.uniform(-1.001, -1)
        if self.obstacle19.obj_start_x < 0 or self.obstacle19.obj_start_x > 500:
            self.obstacle19.obj_velx *= random.uniform(-1.001, -1)
        if self.obstacle20.obj_start_x < 0 or self.obstacle20.obj_start_x > 500:
            self.obstacle20.obj_velx *= random.uniform(-1.001, -1)

        if self.obstacle.obj_start_y < 0 or self.obstacle.obj_start_y > 500 :
            self.obstacle.obj_vely *= -1
        if self.obstacle1.obj_start_y < 0 or self.obstacle1.obj_start_y > 500:
            self.obstacle1.obj_vely *= -1
        if self.obstacle2.obj_start_y < 0 or self.obstacle2.obj_start_y > 500:
            self.obstacle2.obj_vely *= -1
        if self.obstacle3.obj_start_y < 0 or self.obstacle3.obj_start_y > 500:
            self.obstacle3.obj_vely *= -1
        if self.obstacle4.obj_start_y < 0 or self.obstacle4.obj_start_y > 500:
            self.obstacle4.obj_vely *= -1
        if self.obstacle5.obj_start_y < 0 or self.obstacle5.obj_start_y > 500:
            self.obstacle5.obj_vely *= -1
        if self.obstacle6.obj_start_y < 0 or self.obstacle6.obj_start_y > 500:
            self.obstacle6.obj_vely *= -1
        if self.obstacle7.obj_start_y < 0 or self.obstacle7.obj_start_y > 500:
            self.obstacle7.obj_vely *= -1
        if self.obstacle8.obj_start_y < 0 or self.obstacle8.obj_start_y > 500:
            self.obstacle8.obj_vely *= -1
        if self.obstacle9.obj_start_y < 0 or self.obstacle9.obj_start_y > 500:
            self.obstacle9.obj_vely *= -1
        if self.obstacle10.obj_start_y < 0 or self.obstacle10.obj_start_y > 500:
            self.obstacle10.obj_vely *= -1
        if self.obstacle11.obj_start_y < 0 or self.obstacle11.obj_start_y > 500:
            self.obstacle11.obj_vely *= -1
        if self.obstacle12.obj_start_y < 0 or self.obstacle12.obj_start_y > 500:
            self.obstacle12.obj_vely *= -1
        if self.obstacle13.obj_start_y < 0 or self.obstacle13.obj_start_y > 500:
            self.obstacle13.obj_vely *= -1
        if self.obstacle14.obj_start_y < 0 or self.obstacle14.obj_start_y > 500:
            self.obstacle14.obj_vely *= -1
        if self.obstacle15.obj_start_y < 0 or self.obstacle15.obj_start_y > 500:
            self.obstacle15.obj_vely *= -1
        if self.obstacle16.obj_start_y < 0 or self.obstacle16.obj_start_y > 500:
            self.obstacle16.obj_vely *= -1
        if self.obstacle17.obj_start_y < 0 or self.obstacle17.obj_start_y > 500:
            self.obstacle17.obj_vely *= -1
        if self.obstacle18.obj_start_y < 0 or self.obstacle18.obj_start_y > 500:
            self.obstacle18.obj_vely *= -1
        if self.obstacle19.obj_start_y < 0 or self.obstacle19.obj_start_y > 500:
            self.obstacle19.obj_vely *= -1
        if self.obstacle20.obj_start_y < 0 or self.obstacle20.obj_start_y > 500:
            self.obstacle20.obj_vely *= -1

'''
Purpose: to create rules for the game
Instance variables: start_x and obj_start_x: x postion for spacecraft and obstacle, start_y and obj_start_y: y postion for spacecraft and obstacle
start_x_velocity and obj_start_velx: x velocity of spaceship and obstacle, start_y_velocity and obj_start_vely:  y velocity of spaceship and obstacle
Methods: gameloop(self): sets the rules of each object into motion and loops game until it's over.
'''




class SpaceCraft(turtle.Turtle):
    def __init__(self, start_x, start_y, start_x_velocity, start_y_velocity):

        turtle.Turtle.__init__(self)

        self.x_vel = start_x_velocity
        self.y_vel = start_y_velocity
        self.start_x = start_x
        self.start_y = start_y
        self.fuel = 40
#         # spacecraft to face upwards
        self.left(90)
#         # Move spacecraft to initial position without drawing
        self.penup()
        self.speed(0)
        self.goto(start_x, start_y)


    def move(self):
        self.y_vel -= 0.0486
        self.start_x = self.xcor() + self.x_vel
        self.start_y = self.ycor() + self.y_vel
        self.goto(self.start_x, self.start_y)

    def thrust(self):
        if self.fuel > 0:
            self.fuel -= 1
            self.angle = math.radians(self.heading())
            self.x_vel += (math.cos(self.angle))
            self.y_vel += (math.sin(self.angle))
            print(self.fuel)
        else: 
            print("Out of fuel")
    
    def left_turn(self):
        if self.fuel > 0:
            self.fuel -= 1
            print(self.fuel)
            self.left(15)
        else:
            print("Out of fuel!")

    def right_turn(self):
        if self.fuel > 0:
            self.fuel -= 1
            print(self.fuel)
            self.right(15)
        else:
            print("Out of fuel!")

'''
Purpose: To create the movement for the spacecraft
Instance variables: self.fuel: the fuel of the ship, self.start_x: x postion for spacecraft, self.start_y: y postion for spacecraftself.
self.x_vel: x velocity of spaceship, self.y_vel: y velocity of spaceship and obstacle
Methods: move(self): How the spaceship moves due to gravity, thrust(self): the thrust of the spacecraft, 
left_turn(self): the spacecraft will turn left, right_turn(self): the spacecraft will turn right
'''


class Obstacles(turtle.Turtle):
    def __init__(self, obj_start_x, obj_start_y, obj_velx, obj_vely):
        turtle.Turtle.__init__(self)
        self.obj_velx = obj_velx
        self.obj_vely = obj_vely
        self.shape("circle")
        self.color("blue")
        self.penup()
        self.speed(0)
        self.goto(obj_start_x, obj_start_y)
        self.obj_start_x = obj_start_x
        self.obj_start_y = obj_start_y
    def mover1(self):
        if self.obj_velx <= 0 and self.obj_vely <= 0:
            self.obj_velx -= random.uniform(0, 2.1486)
            self.obj_vely -= 0
            self.obj_start_x = self.xcor() + self.obj_velx
            self.obj_start_y = self.ycor() + self.obj_vely
            self.goto(self.obj_start_x, self.obj_start_y)
            if self.obj_velx < -5:
                self.obj_velx = -5
            if self.obj_vely < -5:
                self.obj_vely = -5
        elif self.obj_velx <= 0 and self.obj_vely >= 0:
            self.obj_velx -= random.uniform(0, 2.1486)
            self.obj_vely += 0
            self.obj_start_x = self.xcor() + self.obj_velx
            self.obj_start_y = self.ycor() + self.obj_vely
            self.goto(self.obj_start_x, self.obj_start_y)
            if self.obj_velx < -5:
                self.obj_velx = -5
            if self.obj_vely > 5:
                self.obj_vely = 5
        elif self.obj_velx >= 0 and self.obj_vely <= 0:
            self.obj_velx += random.uniform(0, 2.1486)
            self.obj_vely -= 0
            self.obj_start_x = self.xcor() + self.obj_velx
            self.obj_start_y = self.ycor() + self.obj_vely
            self.goto(self.obj_start_x, self.obj_start_y)
            if self.obj_velx > 5:
                self.obj_velx = 5
            if self.obj_vely < -5:
                self.obj_vely = -5
        else:
            self.obj_velx += random.uniform(0, 2.1486)
            self.obj_vely += 0
            self.obj_start_x = self.xcor() + self.obj_velx
            self.obj_start_y = self.ycor() + self.obj_vely
            self.goto(self.obj_start_x, self.obj_start_y)
            if self.obj_velx > 5:
                self.obj_velx = 5
            if self.obj_vely < 5:
                self.obj_vely = 5

'''
Purpose: To create movement for the obstacles
Instance variables: self.obj_start_x: x postion for obstacle,elf.obj_start_y: y postion for obstacle
self.obj_velx: x velocity of obstacle, self.obj_vely: y velocity of bstacle
Methods: mover1(self): it gives the movement of the obstacle no matter what position
'''

if __name__ == '__main__':
    Game()
