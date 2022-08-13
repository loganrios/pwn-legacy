import React from "react";
import { Box, Avatar, Button } from "@mui/material";
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
}) => {
  return (
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
      <p>{username}</p>
      <Avatar alt={username} src={avatarImage}></Avatar>
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
      <Button variant="outlined" onClick={onEdit} startIcon={<EditIcon />}>
        Edit
      </Button>
      <Button variant="contained" onClick={onReply} endIcon={<ReplyIcon />}>
        Reply
      </Button>
      <p>{timestamp}</p>
    </Box>
  );
};

export default Comment;
