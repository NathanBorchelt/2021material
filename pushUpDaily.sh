#!/bin/bash

DATE=$(date)


DATE+'%c'

PUSH="Push Up on"

echo "What did you do today:  "

read REASON

COMMENT="$PUSH at $DATE from $HOSTNAME. Reason:  $REASON"

cd

cd C:\\Users\\$USER\\Documents\\2021material

read  DATE

git add .



git commit -m "$COMMENT"

git push

