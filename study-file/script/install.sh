#!/bin/bash
sudo cp $(cd `dirname $0`; pwd)/systemd/studyfile.service /etc/systemd/system/
sudo systemctl enable studyfile
sudo systemctl start studyfile