const { GrpcReflection } = require('grpc-js-reflection-client');
const grpc =  require('@grpc/grpc-js');
const readline = require('readline');

const host = "localhost:50051";
try {
    (async () => {
        const c = new GrpcReflection(
            host,
            grpc.ChannelCredentials.createInsecure(),
        );
        const descriptor = await c.getDescriptorBySymbol('DynamicService');

        const packageObject = descriptor.getPackageObject({
            keepCase: true,
            enums: String,
            longs: String
        })

        const proto = new packageObject.DynamicService(
            host,
            grpc.ChannelCredentials.createInsecure(),
        );


        const rl = readline.createInterface({
            input: process.stdin,
            output: process.stdout
        });

        rl.setPrompt('Enter command: ');
        rl.prompt();

        rl.on('line', (input) => {
            switch (input.trim()) {
                case 'add1':
                    proto.AddPerson({
                        name: "abc",
                        age: "10"
                    }, (err, data) => {
                        if (err) {
                            console.log('Error:', err);
                        } else {
                            console.log('Person added:', data);
                        }
                        rl.prompt();
                    });
                    break;
                case 'add2':
                    proto.AddPerson({
                        name: "Kamil",
                        age: "30"
                    }, (err, data) => {
                        if (err) {
                            console.log('Error:', err);
                        } else {
                            console.log('Person added:', data);
                        }
                        rl.prompt();
                    });
                    break;
                case 'list methods':
                    (async () => {
                        const methods = await c.listMethods('DynamicService');
                        console.log(methods.map(method => method.name));
                        rl.prompt();
                    })();
                    break;

                case 'list services':
                    (async () => {
                        const services = await c.listServices();
                        console.log(services);
                        rl.prompt();
                    })();
                    break;
                case 'list':
                    (async () => {
                        const call = proto.GetPersonList();
                        call.on('data', (person) =>{
                            console.log(person);
                        })
                        rl.prompt();
                    })();
                    break;
                case 'update':
                    proto.UpdatePersonAge({
                        name: "Kamil",
                        new_age: "50"
                    }, (err, data) => {
                        if (err) {
                            console.log('Error:', err);
                        } else {
                            console.log('Updated:', data);
                        }
                        rl.prompt();
                    });
                    break;
                case 'exit':
                    rl.close();
                    break;

                default:
                    console.log('Unknown command.');
                    rl.prompt();
                    break;
            }
        }).on('close', () => {
            console.log('Exiting...');
            process.exit(0);
        });
    })();

}catch(e){
    console.log(e);
}


