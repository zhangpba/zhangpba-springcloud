#!/bin/bash
sudo cp $(cd `dirname $0`; pwd)/systemd/studyconfigsever.service /etc/systemd/system/
sudo systemctl enable studyconfigsever
sudo systemctl start studyconfigsever