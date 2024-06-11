const Ice = require("ice").Ice;
const House = require("../../generated/House.js").House;
const readline = require("readline");
async function main() {
    let status = 0;
    let communicator = null;

    try {
        communicator = Ice.initialize();

        const base1 = await communicator.stringToProxy("oven/oven1:tcp -h 127.0.0.2 -p 10000");
        const base2 = await communicator.stringToProxy("light/normalLight:tcp -h 127.0.0.2 -p 10000");
        const base3 = await communicator.stringToProxy("light/RGBLight:tcp -h 127.0.0.2 -p 10000");

        const oven = await House.OvenPrx.checkedCast(base1);
        const normalLight = await House.LightPrx.checkedCast(base2);
        const rgbLight = await House.RGBLightPrx.checkedCast(base3);

        if (!oven || !normalLight || !rgbLight) {
            throw new Error("Invalid proxy");
        }
        do {
            console.log("[CONSOLE]");
            try {
                line = await getInput();

                switch (line) {
                    case "oven":
                        await handleOvenCommands(oven);
                        break;
                    case "normal light":
                        await handleLightCommands(normalLight,'normal');
                        break;
                    case "rgb light":
                        await handleLightCommands(rgbLight,'rgb');
                        break;
                    case "help":
                        console.log("Available commands:");
                        console.log("1. oven");
                        console.log("2. normal light");
                        console.log("1. rgb light");
                        break;
                    default:
                        console.log("Unknown command. Type 'help' for available commands.");
                }
            } catch (error) {
                console.error(error);
            }
        } while (line !== "x");

    } catch (error) {
        console.error(error);
        status = 1;
    } finally {
        if (communicator) {
            try {
                communicator.destroy();
            } catch (error) {
                console.error(error);
                status = 1;
            }
        }
        process.exit(status);
    }
}

async function getInput() {
    const rl = readline.createInterface({
        input: process.stdin,
        output: process.stdout
    });

    return new Promise((resolve, reject) => {
        rl.question("", (answer) => {
            rl.close();
            resolve(answer.trim());
        });
    });
}

async function handleOvenCommands(oven) {
    let line;
    do {
        try {
            console.log("[OVEN]");
            line = await getInput();

            switch (line) {
                case "help":
                    console.log("OVEN: possible commands:");
                    console.log("1. toggle door: open/close oven doors");
                    console.log("2. change interior ");
                    console.log("3. turn on");
                    console.log("4. turn off");
                    console.log("5. change: change parameters of working oven like temp or mode");
                    console.log("6. info: get current oven state");
                    break;
                case "toggle door":
                    try{
                        oven.toggleDoor();
                    }
                    catch(error){
                        if (error instanceof House.WorkingOvenException) {
                            console.error("ERROR: "+error.reason);
                        }
                        console.error("Error: ", error.message);
                    }
                    break;
                case "change interior":
                    console.log("Options: SAFE, UNKNOWN, EMPTY");
                    let interior = await getInput();
                    if (!['SAFE', 'UNKNOWN', 'EMPTY'].includes(interior)) {
                        throw new Error("Invalid value");
                    }
                    oven.changeInterior(interior);
                    break;
                case "turn off":
                    oven.turnOff();
                    break;
                case "turn on":
                    try {
                        console.log("Enter temperature: ");
                        let temp = await getInput();
                        if (!temp || isNaN(temp || temp >250 || temp < 0)) {
                            throw new Error("Invalid temperature. Please enter a valid number.");
                        }

                        console.log("Enter mode (FANOVEN, GRILL, BOTTOMHEATING, CONVENTIONALHEATING): ");
                        let mode = await getInput();
                        if (!['FANOVEN', 'GRILL', 'BOTTOMHEATING', 'CONVENTIONALHEATING'].includes(mode)) {
                            throw new Error("Invalid mode. Please enter one of the following modes: FANOVEN, GRILL, BOTTOMHEATING, CONVENTIONALHEATING.");
                        }

                        console.log("<OPTIONAL> Enter time (in seconds): ");
                        let timeInput = await getInput();
                        let time = timeInput ? parseInt(timeInput) : undefined;

                        await oven.turnOn(temp, mode, time);
                        console.log("Oven started working");

                    } catch (error) {
                        if (error instanceof House.DoorOpenException) {
                            console.error("ERROR: "+error.reason);
                        }
                        else if (error instanceof House.UnsafeInteriorExeption) {
                            console.error("Error: "+ error.reason);
                        }
                        else {
                            console.error("Error:", error.message);
                        }
                    }
                    break;
                case "info":
                    let i = await oven.getInfo();
                    console.log(i);
                    break;
                case "change":
                    console.log("Enter temperature (optional): ");
                    let tempInput = await getInput();
                    let temp1 = tempInput ? parseInt(tempInput) : undefined;

                    console.log("Enter mode (FANOVEN, GRILL, BOTTOMHEATING, CONVENTIONALHEATING, optional): ");
                    let modeInput = await getInput();
                    let mode1 = modeInput ? modeInput : undefined;

                    console.log("Enter time (in seconds, optional): ");
                    let timeInput1 = await getInput();
                    let time1 = timeInput1 ? parseInt(timeInput1) : undefined;

                    let result = await oven.changeSettings(temp1, mode1, time1);
                    if (result){
                        console.log("Oven settings changed successfully.");
                    }
                    else{
                        console.log("No changes made to oven settings");
                    }
                    break;
            }
        } catch (error) {
            console.error(error);
        }
    } while (line !== "x");
}

async function handleLightCommands(light,lightType) {
    let line;
    do {
        try {
            console.log(`[${lightType.toUpperCase()} LIGHT]`);

            line = await getInput();
            switch (line) {
                case "help":
                    console.log(`${lightType.toUpperCase()} LIGHT: possible commands:`);
                    console.log("1. turn on");
                    console.log("2. turn off");
                    console.log("3. set brightness");
                    console.log("4. auto turnoff");
                    console.log("5. info");
                    if (lightType === 'rgb') {
                        console.log("6. set color");
                    }
                    break;
                case "turn on":
                    try{
                        light.turnOn();
                    }
                    catch(error){
                        console.error("Error: ", error.message);
                    }
                    break;
                case "turn off":
                    try{
                        light.turnOff();
                    }
                    catch(error){
                        console.error("Error: ", error.message);
                    }
                    break;
                case "set brightness":
                    try{
                        console.log("Enter brightness level: (1-10)");
                        let b = await getInput();
                        if (b < 1 || b > 10 || isNaN(b)){
                            throw new Error("Ivalid input");
                        }
                        light.setBrightness(parseInt(b));
                        break;
                    }
                    catch (error){
                        console.error(error.message);
                    }
                    break;
                case "auto turnoff":
                    try {
                        console.log("Enter time: ");
                        let timeInput =await getInput();
                        time = parseInt(timeInput);
                        if (isNaN(time) || time < 0){
                            throw new Error("Ivalid input");
                        }
                        light.automaticTurnOff(time).then(result => console.log(result));
                    }
                    catch (error) {
                        console.error("Error:", error.reason);
                    }
                    break;

                case "info":
                    let i = await light.getInfo();
                    console.log(i);
                    break;

                case "set color":
                    if (lightType === 'normal'){break;}
                    console.log("Red: ");
                    let red = await getInput();

                    console.log("Green: ");
                    let green = await getInput();

                    console.log("Blue: ");
                    let blue = await getInput();
                    try{
                        light.setColor(red,green,blue);
                    }
                    catch(error){
                        if (error instanceof House.InputException) {
                            console.error("ERROR: "+error.reason);
                        }
                        else
                            console.error("ERROR: " +error.reason);
                    }
                    break;
                default:
                    console.log("Unknown command. Type 'help' for available commands.");
            }
        } catch (error) {
            console.error(error);
        }
    } while (line !== "x");
}

main();
