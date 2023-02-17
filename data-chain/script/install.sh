#!/bin/bash
sudo cp $(cd `dirname $0`; pwd)/systemd/datachain.service /etc/systemd/system/
sudo systemctl enable datachain
sudo systemctl start datachain