import React from "react";
import {
  Box,
  Link,
  Button,
  IconButton,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  TextField,
} from "@mui/material";

export default function WebLinksList({ links, fields, readingList, onEdit, onSubmit, }) {

  const [open, setOpen] = React.useState(false);

  const handleOpen = () => {
    setOpen(true);
  };
  const handleClose = () => {
    setOpen(false);
  };

  return (
    <Box sx={{ display: "flex", flex: 1, flexDirection: "row", }}>
      <Box sx={{ display: "flex", flex: 1, flexDirection: "column", }}>
        <Link underline="hover"
          color="inherit"
          href={readingList}>
          Reading List
        </Link>
        {links.map((item) => (
            <Box sx={{ display: "flex" }}>
              <Link underline="hover"
                color="inherit"
                href={item.url}>
                {item.label}
              </Link>
            </Box>
        ))}
      </Box>
      <Box>
        <Button variant="outlined" onClick={handleOpen}>
          Edit
        </Button>
        <Dialog open={open} onClose={handleClose} scroll="paper">
          <DialogTitle id="weblinkslist-dialog">Links</DialogTitle>
          <DialogContent sx={{ display: "flex", flexDirection: "column" }}>
            {links.map((item) => (
                <TextField
                  margin="dense"
                  label={item.label}
                  defaultValue={item.url}
                  onChange={() => onEdit(list.label, list.url)}
                />
            ))}
          </DialogContent>
          <DialogActions>
            <Button
              variant="contained"
              onClick={() => {
                onSubmit(); handleClose();
              }}
            >
              Save Changes
            </Button>
            <Button variant="outlined" onClick={handleClose}>
              Cancel
            </Button>
          </DialogActions>
        </Dialog>
      </Box>
    </Box>
  );
};
