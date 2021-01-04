#!/bin/bash
sudo cp $(cd `dirname $0`; pwd)/systemd/studyeureka.service /etc/systemd/system/
sudo systemctl enable studyeureka
sudo systemctl start studyeureka