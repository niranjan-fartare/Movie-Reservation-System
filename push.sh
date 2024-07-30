#!/bin/bash

# Check if a commit message was provided
if [ -z "$1" ]; then
  echo "Error: No commit message provided."
  echo "Usage: ./git_auto_push.sh \"Your commit message\""
  exit 1
fi

# Assign the first argument to the commit message
COMMIT_MESSAGE="$1"

# Add all files
git add .

# Commit with the provided message
git commit -m "$COMMIT_MESSAGE"

# Push to the remote repository
git push

# Notify the user of success
echo "Files added, committed, and pushed successfully!"
