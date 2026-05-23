# 🐍 Snake Game

A classic Snake game built with Java Swing — made to learn the basics of game loops, rendering, and keyboard input.

---

## 🎮 How to Play

- Use **W A S D** to move the snake
- Eat the food to grow longer
- Avoid running into yourself — that's game over!
- The snake wraps around the edges of the screen

---

## ✨ Features

- Custom sprites for the head, body, and food
- Smooth grid-based movement with a game loop timer
- Game Over screen when you hit yourself
- Optional grid overlay (toggle via `showGrid`)
- Custom window icon

---

## 🛠️ Built With

- **Java** — core language
- **Java Swing** — window, panel, and rendering (`JFrame`, `JPanel`, `Graphics2D`)
- **AWT** — keyboard input and image handling

---

## 📁 Project Structure

```
snake/
├── src/
│   ├── Main.java        # Entry point, sets up grid size and cell size
│   └── platform.java    # Game logic, rendering, input handling
├── head.png             # Snake head sprite
├── body.png             # Snake body sprite
├── food.png             # Food sprite (alt)
├── food2.png            # Food sprite (used)
└── icon.png             # Window icon
```

---

## 🚀 Running It

1. Make sure you have **Java** installed
2. Compile from the `snake/src/` directory:
   ```bash
   javac platform.java Main.java
   ```
3. Run from the `snake/` directory (so the images load correctly):
   ```bash
   cd ..
   java -cp src Main
   ```

> The images need to be in the same directory you run the game from.

---

## 🧠 What I Learned

- Setting up a game loop with `javax.swing.Timer`
- Drawing images and shapes with `Graphics2D`
- Handling keyboard input with `KeyListener`
- Basic collision detection and body segment tracking

---

*A fun little project — nothing serious, just learning! 🎉*
