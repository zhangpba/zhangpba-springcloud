#!/bin/bash
sudo cp $(cd `dirname $0`; pwd)/systemd/studycity.service /etc/systemd/system/
sudo systemctl enable studycity
sudo systemctl start studycity