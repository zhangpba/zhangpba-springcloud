#!/bin/bash
sudo cp $(cd `dirname $0`; pwd)/systemd/studyemail.service /etc/systemd/system/
sudo systemctl enable studyemail
sudo systemctl start studyemail