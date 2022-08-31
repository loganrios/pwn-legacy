import React from "react";
import { Box, Link } from "@mui/material";
import { EditIcon } from "@mui/icons-material";

const WebLinks = ({ readingListHref, links }) => {
  return (
    <Box sx={{ display: "flex", flex: 1, flexDirection: "column" }}>
      <Link underline="hover" color="inherit" href={readingListHref}>
        Reading List
      </Link>
      {links.map((link) => (
        <Link underline="hover" color="inherit" href={link.url}>
          {link.label}
        </Link>
      ))}
    </Box>
  );
};

export default WebLinks;
