#!/bin/bash
sudo cp $(cd `dirname $0`; pwd)/systemd/studystream.service /etc/systemd/system/
sudo systemctl enable studystream
sudo systemctl start studystream