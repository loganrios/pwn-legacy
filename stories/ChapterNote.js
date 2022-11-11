import React from "react";
import { Typography, Box } from "@mui/material";
import AutoStoriesIcon from "@mui/icons-material/AutoStories";

const ChapterNote = ({ text, isVisible }) => {
  return isVisible ? (
    <Box
      sx={{
        display: "flex",
        flexDirection: "row",
        justifyContent: "left",
        typography: "p",
        mx: "auto",
        width: "75%",
        height: "75%",
        backgroundColor: "background.paper",
        boxShadow: 2,
        borderRadius: 3,
        visibility: { isVisible },
      }}
    >
      <Box sx={{ m: 1 }}>
        <AutoStoriesIcon />
      </Box>
      <Box sx={{ mx: "auto" }}>{text}</Box>
    </Box>
  ) : null;
};

export default ChapterNote;
