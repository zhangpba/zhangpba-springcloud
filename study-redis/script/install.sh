#!/bin/bash
sudo cp $(cd `dirname $0`; pwd)/systemd/studyredis.service /etc/systemd/system/
sudo systemctl enable studyredis
sudo systemctl start studyredis