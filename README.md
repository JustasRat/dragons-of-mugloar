# Dragons of Mugloar game client
Game client which will be performing game actions automatically. Goal is to achieve 1000 score.\
Main points of execution logic:
* Find and execute easiest messages (quests).
* If we lost lives then try to heal.
* If next message is of high risk then try to buy buffs.
* Execute until dead.

# Configuration
External REST services host name is parameterized in _application.properties_:\
_service.url=https://dragonsofmugloar.com_

# Run

Execute command in root directory:\
_mvn install && mvn spring-boot:run -pl client_