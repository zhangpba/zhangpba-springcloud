#!/bin/bash
sudo cp $(cd `dirname $0`; pwd)/systemd/studyconfigclient.service /etc/systemd/system/
sudo systemctl enable studyconfigclient
sudo systemctl start studyconfigclient