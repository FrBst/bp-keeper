# Blood Pressure Keeper
Telegram bot for blood pressure logging.
Created to be fast and simple, and basically tailored for my own specific use case

## Usage
Create a `.env` file in root repository directory containing the environment variables:
- **BP_KEEPER_NAME** - the name of the Telegram bot;
- **BP_KEEPER_TOKEN** - the token received from Botfather.

Then run the `run.sh` script in the terminal, which will create and start a Docker container with all the required components.

## References
https://github.com/vishnubob/wait-for-it is the script I use to wait for the database to start accepting connections.
