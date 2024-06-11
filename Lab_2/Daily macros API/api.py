from fastapi import FastAPI, Form, HTTPException
from fastapi.responses import HTMLResponse, JSONResponse
from pydantic import BaseModel
import requests

app = FastAPI()
@app.get("/")
async def root():
    with open("index.html", "r") as file:
        html_content = file.read()
    return HTMLResponse(content=html_content)

class SportData(BaseModel):
    age: int
    gender: str
    height: int
    weight: int
    activity: int
    goal: str

@app.post("/macros")
async def hello(age: int = Form(...), gender: str = Form(...), height: int = Form(...), weight: int = Form(...),
                activity: int = Form(...), goal: str = Form(...), ingr: str = Form(...),diet:str=Form(...), api_key:str=Form(...)):

    sport_data = SportData(age=age, gender=gender,height=height, weight=weight, activity=activity, goal=goal)
    sport_response = await sport_request(sport_data, api_key)
    macros = sport_response['data'][diet] # protein, carbs, fat

    ingredient_info = await ingredient_request(ingr, api_key)
    ingredient_protein = ingredient_info['PROCNT']['quantity']
    ingredient_fat = ingredient_info['FAT']['quantity']
    ingredient_carbs = ingredient_info['CHOCDF']['quantity']

    html_content = create_html_content(ingr,
                                       diet,
                                       goal,
                                       ingredient_protein,
                                       macros['protein'],
                                       ingredient_fat,
                                       macros['fat'],
                                       ingredient_carbs,
                                       macros['carbs'])
    dict_content = {"ingr": ingr, "diet": diet, "goal": goal, "ingredient_protein": macros['protein'],"ingredient_fat":macros['fat'],"ingredient_carbs":macros['carbs']}
    # Return the HTML response
    # please reload
    return JSONResponse(content=dict_content)

# makes request to the api to get all info
async def sport_request(sport_data: SportData, api_key: str):
    url = "https://fitness-calculator.p.rapidapi.com/macrocalculator"

    querystring = {"age": sport_data.age, "gender": sport_data.gender, "height": sport_data.height, "weight": sport_data.weight, "activitylevel": sport_data.activity,
                   "goal": sport_data.goal}

    headers = {
        "X-RapidAPI-Key": api_key,
        "X-RapidAPI-Host": "fitness-calculator.p.rapidapi.com"
    }

    response = requests.get(url, headers=headers, params=querystring)
    if response.status_code != 200:
        raise HTTPException(status_code=response.status_code, detail=response.json())
    return response.json()


async def ingredient_request(ingr: str, api_key: str):
    url = "https://edamam-edamam-nutrition-analysis.p.rapidapi.com/api/nutrition-data"
    querystring = {"ingr": ingr}
    headers = {
        "X-RapidAPI-Key": api_key,
        "X-RapidAPI-Host": "edamam-edamam-nutrition-analysis.p.rapidapi.com"
    }

    response = requests.get(url, headers=headers, params=querystring)

    if response.ok:
        data = response.json()
        if not data or data['calories'] == 0:
            raise HTTPException(status_code=422, detail="Invalid data format: ingredient")
        return data['totalNutrients']
    else:
        raise HTTPException(status_code=response.status_code, detail=response.text)

def create_html_content(ingredient:str, diet:str, goal:str, ingredient_protein:float, protein_macro:float, ingredient_fat:float, fat_macro:float,
                        ingredient_carbs:float, carbs_macro:float):
    return f"""
    <!DOCTYPE html>
    <html>
    <head>
        <title>Ingredient: {ingredient}</title>
    </head>
    <body>
        <h1>Ingredient: {ingredient}</h1>
        <h3>Diet type: {diet}</h3>
        <h3> Goal: {goal}</h3>
        <p>Daily protein: {round(100 * ingredient_protein / protein_macro, 2)}%</p>
        <p>Daily fat: {round(100 * ingredient_fat / fat_macro, 2)}%</p>
        <p>Daily carbohydrates: {round(100 * ingredient_carbs / carbs_macro, 2)}%</p>
    </body>
    </html>
    """