import axios from "axios";
import React from "react";
import {Button, Chip} from "@mui/material";

enum Cell { X = 'X', O = 'O', Empty = 'Empty' }

enum Player { X = 'X', O = 'O' }

type TicTacToeMove = { // <-- The type of the move coming from the server
    player: Player,
    board: Cell[][],
    winner: Player | null,
    valid: boolean,
    draw: boolean,
    gameOver: boolean,
}

const newGame = () => axios.get('http://localhost:8080/startGame').then(res => res.data); // <-- request the start of a new game from the server

// send a move to the server and request the updated game state, using url params in a post request
const makeMove = (i: number, j: number) => axios.post(`http://localhost:8080/move/${i}/${j}`).then(res => res.data);

export const App = () => {
    const [move, setMove] = React.useState<TicTacToeMove | null>(null); // <-- the current game state

    React.useEffect(() => void newGame().then(setMove), []); // <-- request the start of a new game from the server at the start of the app (void means no return value)

    const Square = ({cell, row, col}: { cell: Cell, row: number, col: number }) => { // <-- renders a square for the board
        return <Button
            onClick={() => cell === Cell.Empty && makeMove(row, col).then(setMove)} // <-- onClick handler to make a move, but only if the cell is empty
            color={cell == Cell.X ? 'error' : cell == Cell.O ? 'success' : 'primary'}
            style={{width: '150px', height: '150px', fontSize: "30px"}}
            variant="contained">
            {cell == Cell.X ? 'X' : cell == Cell.O ? 'O' : '-'}
        </Button>;
    }

    const Outcome = ({winner}: { winner: Player | null }) => <>  {/* <-- renders the outcome of the game (winner, draw, or game not over) */}
        <Chip label={winner === null ? 'Draw' : winner === Player.X ? 'X wins' : 'O wins'}
              color={winner === null ? 'default' : winner === Player.X ? 'error' : 'success'}
              variant="outlined"
              style={{margin: '20px', fontSize: "30px"}}/>
        <Button
            onClick={() => newGame().then(setMove)} // <-- request a new game from the server in click and sets the move state
            color="primary"
            variant="contained"
            style={{margin: '20px'}}>
            New Game
        </Button>
    </>

    const CurrentPlayer = ({player}: { player: Player }) => // <-- renders the current player
        <Chip label={player === Player.X ? '   X   ' : '   O   '}
              color={player === Player.X ? 'error' : 'success'}
              variant="outlined"
              style={{margin: '20px', fontSize: "30px"}}/>

    if (!move)  // <-- if the game state is not yet available, show a loading message
        return <div>Loading...</div>;

    return <div style={{textAlign: "center"}}> {/* <-- renders the game board and outcome using the components above */}
        <div>
            <Square cell={move.board[0][0]} row={0} col={0}/>
            <Square cell={move.board[0][1]} row={0} col={1}/>
            <Square cell={move.board[0][2]} row={0} col={2}/>
        </div>
        <div>
            <Square cell={move.board[1][0]} row={1} col={0}/>
            <Square cell={move.board[1][1]} row={1} col={1}/>
            <Square cell={move.board[1][2]} row={1} col={2}/>
        </div>
        <div>
            <Square cell={move.board[2][0]} row={2} col={0}/>
            <Square cell={move.board[2][1]} row={2} col={1}/>
            <Square cell={move.board[2][2]} row={2} col={2}/>
        </div>

        <div>
            {move.gameOver && <Outcome winner={move.winner}/>}
        </div>

        <div>
            <CurrentPlayer player={move.player}/>
        </div>

    </div>
};