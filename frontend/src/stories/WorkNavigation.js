import React from "react";
import {
  Button,
  Switch,
  FormGroup,
  FormControlLabel,
  Box,
  TextField,
  Grid,
  Paper,
} from "@mui/material";

const ToggleLabel = ({ onChange, checked, disabled }) => {
  return (
    <FormControlLabel
      control={
        <Switch checked={checked} onChange={onChange} disabled={disabled} />
      }
      label="Track Progress"
      labelPlacement="start"
      sx={{ mx: "auto" }}
    />
  );
};

const TrackingToggleLabel = ({ onToggleTrack, isTracked, canToggleTrack }) => {
  return canToggleTrack ? (
    <ToggleLabel
      onChange={onToggleTrack}
      checked={isTracked}
      disabled={false}
    />
  ) : (
    <ToggleLabel onChange={null} checked={false} disabled={true} />
  );
};

const WorkNavigation = ({
  onNext,
  onPrev,
  onChapterSelect,
  isTracked,
  onToggleTrack,
  canToggleTrack,
  currentChapter,
  chapters,
}) => {
  return (
    <Box
      sx={{
        flexGrow: 1,
        overflow: "hidden",
        px: 0.1,
      }}
    >
      <Paper
        sx={{
          my: 0.1,
          mx: "auto",
          p: 0.1,
          maxWidth: 600,
        }}
      >
        <Grid container justifyContent="center" alignItems="center" spacing={1}>
          <Grid item>
            <Button variant="contained" onClick={onPrev}>
              Prev
            </Button>
          </Grid>
          <Grid item xs flexGrow={1}>
            <TextField
              id="chapter-selection"
              select
              label="Chapter"
              defaultValue={currentChapter}
              onChange={onChapterSelect}
              SelectProps={{
                native: true,
              }}
              variant="standard"
              margin="dense"
              fullWidth
            >
              {chapters.map((option) => (
                <option key={option.value} value={option.value}>
                  {option.label}
                </option>
              ))}
            </TextField>
          </Grid>
          <Grid item xs={12} sm="auto" order={{ xs: 4, sm: 3 }}>
            <FormGroup>
              <TrackingToggleLabel
                onToggleTrack={onToggleTrack}
                canToggleTrack={canToggleTrack}
                isTracked={isTracked}
              />
            </FormGroup>
          </Grid>
          <Grid item order={{ xs: 3, sm: 4 }}>
            <Button variant="contained" onClick={onNext}>
              Next
            </Button>
          </Grid>
        </Grid>
      </Paper>
    </Box>
  );
};
export default WorkNavigation;
