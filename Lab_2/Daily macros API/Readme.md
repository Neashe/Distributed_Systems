# Daily Macros API
:poultry_leg:
:butter:
:rice:

## About
The Daily Macros API allows you to check how a specific ingredient covers your daily demand for macronutrients (carbohydrates, proteins , fats). This app merges two existing APIs:

1. Edamam Nutrition Analysis API
2. Fitness Calculator API

Both APIs can be found on [RapidAPI](https://rapidapi.com).
### Get API KEY
The API can be obtained from RapidAPI website.

## Development environment
1. Python version: 3.11
2. Initializing virtual Python environment:
```
virtualenv env
```
### Activating virtual env (Mac/Linux)
```
source ./env/bin/activate 
```
### Activating virtual env (Windows)
```
./env/Scripts/activate
```
3. Installing development dependencies:
```
pip install -r requirments.txt
```
4. Run
```
uvicorn api:app --reload
```
## Usage
Once the server is running, you can use the API to analyze nutritional content and calculate daily macronutrient coverage.
### API Endpoints
POST /macros

To check usage example you can either 

1. use form visible on http://127.0.0.1:8000
2. navigate to Swagger UI: http://localhost:8000/docs
