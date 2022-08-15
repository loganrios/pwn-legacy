import React from "react";
import {
  Box,
  Avatar,
  Button,
  Paper,
  Grid,
  IconButton,
  Typography,
} from "@mui/material";
import EditIcon from "@mui/icons-material/Edit";
import ReplyIcon from "@mui/icons-material/Reply";

const Comment = ({
  username,
  avatar,
  timestamp,
  onEdit,
  onReply,
  commentText,
  avatarImage,
  isOwner,
  isLocked,
}) => {
  return (
    <Box
      sx={{
        flexGrow: 1,
        overflow: "hidden",
        px: 0.1,
        /* display: "flex", */
        /* flexDirection: "row", */
        /* justifyContent: "left", */
        /* typography: "p", */
        /* mx: "auto", */
        /* backgroundColor: "background.paper", */
        /* boxShadow: 2, */
        /* borderRadius: 3, */
      }}
    >
      <Paper
        sx={{
          my: 0.1,
          mx: "auto",
          p: 0.1,
          maxWidth: 800,
        }}
      >
        <Grid container justifyContent="center" alignItems="center" spacing={1}>
          <Grid item>
            <Box>
              <Avatar
                alt={username}
                src={avatarImage}
                sx={{ mx: "auto" }}
              ></Avatar>
              <p>{username}</p>
            </Box>
          </Grid>
          <Grid item xs={12} sm container flexGrow={1}>
            <Box
              sx={{
                display: "flex",
                flexDirection: "row",
                justifyContent: "left",
                typography: "p",
                mx: "auto",
                backgroundColor: "background.paper",
                boxShadow: 2,
                borderRadius: 3,
              }}
            >
              <p>{commentText}</p>
            </Box>
          </Grid>
          <Grid item>
            {isOwner ? (
              <IconButton aria-label="edit" onClick={onEdit}>
                <EditIcon />
              </IconButton>
            ) : null}
            {isLocked ? null : (
              <IconButton aria-label="reply" onClick={onReply}>
                <ReplyIcon />
              </IconButton>
            )}
          </Grid>
          <Grid item>
            <p>{timestamp}</p>
          </Grid>
        </Grid>
      </Paper>
    </Box>
  );
};

export default Comment;
