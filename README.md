# TicTacToeSpring

Final project for the Adecco Java Academy.

The goal of the project is to create a game of TicTacToe with a Spring framework as backend and a React frontend.

Specifications:

- Spring Application
- Made with Spring Boot
- Backend:
    - Must manage the game state and the game logic
    - For each move it saves the game state in a database:
        - the current status of the board
        - the current player
        - winning/draw/none move
        - game ID

    - The available rests should be:
        - make a new game
        - make a move

    - Checks for invalid statuses


- Frontend:
    - present the board as a grid
    - show the current status of the board
    - allow the user to make a move

## Usage for this example project

Clone the repository and run the following command:

```shell
git clone https://github.com/DrTtnk/TicTacToeSpring.git
cd TicTacToeSpring
```

To start MySQL in the docker, open a new terminal and run the following command:

```shell
docker run --name mysql -e MYSQL_ROOT_PASSWORD=1234 -p 3306:3306 mysql
```

To start the frontend, open a new terminal and run the following command:

```shell
cd frontend
npm install
npm run start
```
