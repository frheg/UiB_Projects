from uib_inf100_graphics import *
import random
def get_color(value):
        if value < 0:
            return "cyan"
        if value == 0:
            return "lightgray"
        if value > 0:
            return "orange"

def draw_board(canvas, x1, y1, x2, y2, board, debug_mode):  # DRAW BOARD
    rows = len(board)
    cols = len(board[0])
    cell_width = (x2 - x1) / cols
    cell_height = (y2 - y1) / rows
    for row in range(rows):
        for col in range(cols):
            color = get_color(board[row][col])
            cell_x1 = x1 + col * cell_width
            cell_y1 = y1 + row * cell_height
            cell_x2 = cell_x1 + cell_width
            cell_y2 = cell_y1 + cell_height
            cell_mid_x = (cell_x1 + cell_x2) / 2
            cell_mid_y = (cell_y1 + cell_y2) / 2
            canvas.create_rectangle(cell_x1, cell_y1, cell_x2, cell_y2, fill=color, outline="black")
            if debug_mode == True:
                canvas.create_text(cell_mid_x, cell_mid_y, text=f"{row}, {col}\n{board[row][col]}", font="Arial 12", fill="black")

def app_started(app):   # APP STARTED
    app.board = [
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, -1, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 1, 2, 3, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
    ]
    app.debug_mode = False
    app.snake_size = 3
    app.head_pos = (3, 4)
    app.direction = "east"
    app.state = "active"    # Antar app.state må være definert her
    app.timer_delay = 200

def get_next_head_position(head_row, head_col, direction):  # GET NEXT HEAD POSITION
    if direction == "north":
        head_row -= 1
    if direction == "south":
        head_row += 1
    if direction == "east":
        head_col += 1
    if direction == "west":
        head_col -= 1
    return (head_row, head_col)

def timer_fired(app):  # TIMER FIRED
     if app.debug_mode == False and app.state == "active":
        move_snake(app)

def subtract_one_from_all_positives(grid):
    for i in grid:
        for j, value in enumerate(i):
            if value > 0:  # Sjekker om positivt tall
                i[j] = value - 1

def add_apple_at_random_location(grid):
    appBoardRow = random.choice(range(len(grid)))
    appBoardCol = random.choice(range(len(grid[0])))
    while grid[appBoardRow][appBoardCol] == 0:
        grid[appBoardRow][appBoardCol] -= 1
        return grid[appBoardRow][appBoardCol]
    else:
        add_apple_at_random_location(grid)

def move_snake(app):
    app.head_pos = get_next_head_position(app.head_pos[0], app.head_pos[1], app.direction)
    row, col = app.head_pos     # Oppdaterer app.head_pos
    if not is_legal_move(row, col, app.board):
        app.state = "gameover"
        return
    if app.board[row][col] == -1: # Sjekker om hodet er på posisjon med et eple
        app.snake_size += 1
        add_apple_at_random_location(app.board)
    else:
        subtract_one_from_all_positives(app.board)
    app.board[row][col] = app.snake_size 
    #return app.head_pos

def key_pressed(app, event):    # KEY PRESSED
    if event.key == "d":
        app.debug_mode = not app.debug_mode

    if app.state == "active":
        if event.key == "Space":
            move_snake(app) 
        if event.key == "Up": 
            app.direction = "north"
        if event.key == "Down":
            app.direction = "south"
        if event.key == "Left":
            app.direction = "west"
        if event.key == "Right":
            app.direction = "east"

def is_legal_move(row, col, board):
    if row > len(board)-1 or col > len(board[0])-1:
        return False
    if row < 0 or col < 0:
        return False
    if board[row][col] > 0:
        return False
    else: 
        return True

def redraw_all(app, canvas):    # REDRAW ALL
    draw_board(canvas, 25, 25, app.width-25, app.height-25, app.board, app.debug_mode)
    if app.debug_mode == True:
        canvas.create_text(app.width/2, 10, text=f'{app.head_pos=} {app.snake_size=} {app.direction=} {app.state=}', font="Arial 12", fill="black")
    elif app.state == "gameover":
        canvas.create_text(app.width/2, app.height/2, text="Game Over!", fill="black", font="Arial 30")

run_app(width=500, height=400, title="Snake")