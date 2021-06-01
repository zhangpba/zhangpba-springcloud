#!/bin/bash
sudo cp $(cd `dirname $0`; pwd)/systemd/studyzuul.service /etc/systemd/system/
sudo systemctl enable studyzuul
sudo systemctl start studyzuul