#!/bin/bash
sudo cp $(cd `dirname $0`; pwd)/systemd/studyuser.service /etc/systemd/system/
sudo systemctl enable studyuser
sudo systemctl start studyuser